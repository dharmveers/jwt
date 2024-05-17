package com.auth.jwt.services;

import com.auth.jwt.dtos.LoginUsrDto;
import com.auth.jwt.dtos.RegisterUsrDto;
import com.auth.jwt.model.Usr;
import com.auth.jwt.repositories.UsrRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsrRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsrRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    public Usr signUp(RegisterUsrDto usrDto){
        Usr usr = new Usr();
        usr.setName(usrDto.getFullName());
        usr.setEmail(usrDto.getEmail());
        usr.setPassword(usrDto.getPassword());
        return userRepository.save(usr);
    }

    public Usr authenticate(LoginUsrDto usrDto){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(usrDto.getEmail(), usrDto.getPassword());
        authenticationManager.authenticate(authToken);
        return userRepository.findByEmail(usrDto.getEmail());
    }
}
