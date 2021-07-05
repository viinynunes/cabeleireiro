package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import br.com.davicabeleireiro.davicabeleireiro.repository.ClientRepository;
import br.com.davicabeleireiro.davicabeleireiro.security.AccountCredentialsClientDTO;
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
@RequestMapping("/client/signing")
public class AuthClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity signing(@RequestBody AccountCredentialsClientDTO dto){

        try {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        Client client = clientRepository.findByEmail(dto.getEmail());

        var token = "";

        if (client != null){
            token = tokenProvider.createToken(dto.getEmail(), client.getRoles());
        }else {
            throw new ResourceNotFoundException("username "+ dto.getEmail() + " not found");
        }

        Map<Object, Object> model = new HashMap<>();

        model.put("username", dto.getEmail());
        model.put("token", token);

        return ok(model);

        } catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping
    public String Home(){
        return "Login Page";
    }
}
