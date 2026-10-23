package com.devcoresolutions.thepixelvault.config;

import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.repository.UsersRepository;
import com.devcoresolutions.thepixelvault.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

 private final UsersRepository usersRepository;

 @Bean
 public UserDetailsService userDetailsService(){
  return email -> {
   Users user = usersRepository.findByEmail(email).orElseThrow(
           () -> new UsernameNotFoundException("Email no registrado: " + email)
   );
   return new org.springframework.security.core.userdetails.User(
           user.getEmail(),
           user.getPassword(),
           List.of(new SimpleGrantedAuthority("ROLE_" + user.getIdUserRole().getRoleName()))
   );
  };
 }

 @Bean
 public PasswordEncoder passwordEncoder(){
  return new BCryptPasswordEncoder();
 }

 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
  return config.getAuthenticationManager();
 }

 @Bean
 public CorsConfigurationSource corsConfigurationSource() {
  CorsConfiguration config = new CorsConfiguration();
  config.setAllowedOrigins(List.of("http://localhost:5500", "http://127.0.0.1:5500")); // origen del front (Aquí cambiamos el puerto según el server)
  config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
  config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  source.registerCorsConfiguration("/**", config);
  return source;
 }

 // Configuración de bypass total para tus pruebas en Bruno
 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                JwtAuthenticationFilter jwtFilter) throws Exception {
  http
          .cors(Customizer.withDefaults())
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/api/auth/**").permitAll()
                  .requestMatchers(HttpMethod.GET,"/api/products","/api/products/**","/api/categories","/api/categories/**").permitAll()
                  .anyRequest().authenticated())
          .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .exceptionHandling(e -> e.authenticationEntryPoint(
                  new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }
}
