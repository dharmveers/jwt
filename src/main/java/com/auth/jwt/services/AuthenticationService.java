package com.auth.jwt.services;

import com.auth.jwt.dtos.LoginUsrDto;
import com.auth.jwt.dtos.RegisterUsrDto;
import com.auth.jwt.model.User;
import com.auth.jwt.repositories.UsrRepository;
import com.auth.jwt.responses.ResponseMsg;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    public ResponseMsg signUp(RegisterUsrDto usrDto){
        User dbUser = userRepository.findByEmail(usrDto.getEmail());
        System.out.println("Full Name :"+usrDto.getFullName());
        if(dbUser==null){
            User user = new User();
            user.setFullName(usrDto.getFullName());
            user.setEmail(usrDto.getEmail());
            user.setPassword(passwordEncoder.encode(usrDto.getPassword()));
            userRepository.save(user);
            return new ResponseMsg("Register Successfully","200");
        }
        return new ResponseMsg("User Already Exists","403");
    }

    public User authenticate(LoginUsrDto usrDto){
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(usrDto.getEmail(), usrDto.getPassword());
        try {
            Authentication authenticate = authenticationManager.authenticate(authToken);
            if(authenticate.isAuthenticated()){
                return userRepository.findByEmail(usrDto.getEmail());
            }
        }catch (AuthenticationException authenticationException){
            System.out.println("Exception "+authenticationException.getMessage());
        }
      return null;
    }
}
