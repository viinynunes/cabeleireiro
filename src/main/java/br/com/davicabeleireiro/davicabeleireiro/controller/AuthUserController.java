package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import br.com.davicabeleireiro.davicabeleireiro.repository.UserRepository;
import br.com.davicabeleireiro.davicabeleireiro.security.AccountCredentialsUserDTO;
import br.com.davicabeleireiro.davicabeleireiro.security.jwt.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("admin/signing")
public class AuthUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity signing(@RequestBody AccountCredentialsUserDTO dto){

        String exceptionMessage = "Invalid Username or Password";

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
            User user = userRepository.findByUserName(dto.getUsername());

            for (String p : user.getRoles()){
                if (p.equalsIgnoreCase("client") & user.getRoles().size() <= 1)
                    throw new BadCredentialsException(exceptionMessage = "User not allowed");
            }

            var token = "";

            if (user != null){
                token = tokenProvider.createToken(dto.getUsername(), user.getRoles());
            }else {
                throw new ResourceNotFoundException("username "+ dto.getUsername() + " not found");
            }

            Map<Object, Object> model = new HashMap<>();

            model.put("username", dto.getUsername());
            model.put("token", token);

            return ok(model);

        } catch (AuthenticationException e){
            throw new BadCredentialsException(exceptionMessage);
        }
    }

    @GetMapping
    public String Home(){
        return "Login Page";
    }
}
