package com.auth.jwt.controllers;

import com.auth.jwt.dtos.LoginUsrDto;
import com.auth.jwt.dtos.RegisterUsrDto;
import com.auth.jwt.model.User;
import com.auth.jwt.responses.LoginResponse;
import com.auth.jwt.responses.ResponseMsg;
import com.auth.jwt.services.AuthenticationService;
import com.auth.jwt.services.JwtService;
import com.auth.jwt.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("auth")
public class JwtAuthController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
    @GetMapping("/hello")
    public String getHello(){
        return "Hello jwtApplication";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUsrDto registerUserDto) {
        String status = Validation.signupUserCheck(registerUserDto);
        if(status.equals("success")){
            return ResponseEntity.ok(authenticationService.signUp(registerUserDto));
        }
        return ResponseEntity.ok(new ResponseMsg(status,"400"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUsrDto loginUserDto) {
<<<<<<< HEAD
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        if(authenticatedUser!=null){
            LoginResponse loginResponse = new LoginResponse();
=======
        String status = Validation.loginCheck(loginUserDto);
        if (status.equals("success")){
            Usr authenticatedUser = authenticationService.authenticate(loginUserDto);
            if(authenticatedUser!=null){
                LoginResponse loginResponse = new LoginResponse();
>>>>>>> d00a4f2a1a20ebfe1550630bf5efdd29ab35da72

                loginResponse.setToken(jwtService.generateToken(authenticatedUser));
                loginResponse.setExpiresIn(jwtService.getExpirationTime());
                return ResponseEntity.ok(loginResponse);
            }else{
                return ResponseEntity.ok(new ResponseMsg("Invalid userid or password","401"));
            }
        }
        return ResponseEntity.ok(new ResponseMsg(status,"403"));
    }
}
