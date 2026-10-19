package com.devcoresolutions.thepixelvault.config;

import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.repository.UsersRepository;
import com.devcoresolutions.thepixelvault.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

 private final UsersRepository usersRepository;

 // 1. DESCOMENTADO: Necesario para que JwtAuthenticationFilter pueda compilar sin errores
 @Bean
 public UserDetailsService userDetailsService(){
  return email -> {
   Users user = usersRepository.findByEmail(email).orElseThrow(
           () -> new UsernameNotFoundException("Email no registrado: " + email)
   );
   return new org.springframework.security.core.userdetails.User(
           user.getEmail(),
           user.getPassword(),
           Collections.emptyList()
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

 // Configuración de bypass total para tus pruebas en Bruno
 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
                  .anyRequest().permitAll()
          )
          .sessionManagement(session -> session
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          );
  return http.build();
 }
}
