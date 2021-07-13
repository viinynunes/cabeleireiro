package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.UserDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import br.com.davicabeleireiro.davicabeleireiro.security.AccountCredentialsUserDTO;
import br.com.davicabeleireiro.davicabeleireiro.security.jwt.JWTTokenProvider;
import br.com.davicabeleireiro.davicabeleireiro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/client")
public class AuthClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @PostMapping(value = "/signing", produces = "application/json", consumes = "application/json")
    public ResponseEntity signing(@RequestBody AccountCredentialsUserDTO dto) {

        String exceptionMessage = "Invalid Username or Password";

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

            var user = (User) userService.loadUserByUsername(dto.getUsername());

            for (String p : user.getRoles()){
                if (p.equalsIgnoreCase("client")){
                    String token = "";

                    if (user != null) {
                        token = tokenProvider.createToken(token, user.getRoles());
                    } else {
                        throw new ResourceNotFoundException("Username " + dto.getUsername() + " found");
                    }

                    Map<Object, Object> map = new HashMap<>();
                    map.put("user", dto.getUsername());
                    map.put("token", token);

                    return ok(map);
                }
            }

            throw new BadCredentialsException(exceptionMessage = "User not Allowed");
        } catch (Exception e) {
            throw new BadCredentialsException(exceptionMessage);
        }
    }

    @GetMapping
    public String Home() {
        return "Login Page";
    }


}
