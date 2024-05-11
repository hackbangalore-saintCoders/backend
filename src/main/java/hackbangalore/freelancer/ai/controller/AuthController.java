package hackbangalore.freelancer.ai.controller;


import hackbangalore.freelancer.ai.model.UserCred;
import hackbangalore.freelancer.ai.model.UserCredential;
import hackbangalore.freelancer.ai.model.UserRegistrationRequest;
import hackbangalore.freelancer.ai.security.JwtHelper;
import hackbangalore.freelancer.ai.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/freelancer")
public class AuthController {


    @Autowired
    AuthService authService;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/healthcheck")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Collections.singletonMap("health_check", "true"));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegistrationRequest request) {
        boolean success = authService.createUserCredential(request);
        System.out.println(success);

        return (!success) ? ResponseEntity.badRequest().build() :  ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCred userCred){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCred.getEmail(),userCred.getPwd())
        );

        if(authentication.isAuthenticated()) {

            UUID getId = authService.getUUIDFromEmail(userCred.getEmail());

            return ResponseEntity.ok(jwtHelper.generateToken(String.valueOf(getId), userCred.getEmail()));
        }
        throw new RuntimeException("user invalid access");



    }
}

