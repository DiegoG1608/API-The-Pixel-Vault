package com.devcoresolutions.thepixelvault.services;


import com.devcoresolutions.thepixelvault.dtos.RegisterRequest;
import com.devcoresolutions.thepixelvault.dtos.ResponseRequest;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseRequest registerUser(RegisterRequest registerRequest){
        //Creamos un usuario para mapear la información que viene en el DTO
        Users users = new Users();
        users.setEmail(registerRequest.email());
        //Encriptamos la contraseña con el password Encoder
        users.setPassword(passwordEncoder.encode(registerRequest.password()));
        users.setUserName(registerRequest.userName());
        users.setUserMiddleName(registerRequest.userMiddleName());
        users.setUserPhone(registerRequest.userPhone());
        users.setUserLastNameFirst(registerRequest.userLastNameFirst());
        users.setStatus(registerRequest.status());
        users.setUserLastNameSecond(registerRequest.userLastNameSecond());
        users.setUserAddress(registerRequest.userAddress());
        usersRepository.save(users);
        String token = jwtService.generateToken(users.getEmail());
        return new ResponseRequest(token);
    }
}
