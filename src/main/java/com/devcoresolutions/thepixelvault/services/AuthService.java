package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.dtos.AuthResponse;
import com.devcoresolutions.thepixelvault.dtos.LoginRequest;
import com.devcoresolutions.thepixelvault.dtos.RegisterRequest;
import com.devcoresolutions.thepixelvault.dtos.ResponseRequest;
import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.repository.UserRoleRepository;
import com.devcoresolutions.thepixelvault.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AuthService {
    private static final String DEFAULT_ROLE = "CLIENTE";

    private final UsersRepository usersRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseRequest registerUser(RegisterRequest registerRequest){

        if (usersRepository.existsByEmail(registerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está registrado");
        }
        UserRole role = userRoleRepository.findByRoleName(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException("No existe el rol " + DEFAULT_ROLE));

        //Creamos un usuario para mapear la información que viene en el DTO
        Users user = new Users();
        user.setEmail(registerRequest.email());
        //Encriptamos la contraseña con el password Encoder
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setUserName(registerRequest.userName());
        user.setUserMiddleName(registerRequest.userMiddleName());
        user.setUserPhone(registerRequest.userPhone());
        user.setUserLastNameFirst(registerRequest.userLastNameFirst());
        user.setStatus((byte) 1);
        user.setUserLastNameSecond(registerRequest.userLastNameSecond());
        user.setUserAddress(registerRequest.userAddress());
        user.setIdUserRole(role);
        usersRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        return new ResponseRequest(token);
    }

    public AuthResponse loginUser(LoginRequest  loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email o contraseña incorrectos");
        }
        return new AuthResponse(jwtService.generateToken(loginRequest.email()));
    }
}
