package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.UserDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserDTO create(@RequestBody UserDTO dto){
        return userService.create(dto);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public UserDTO update(@PathVariable("id") Long id,
                          @RequestBody UserDTO dto){
        dto.setId(id);
        return userService.update(dto);
    }

    @PatchMapping(value = "/disable/{id}", consumes = "application/json")
    public UserDTO disable(@PathVariable("id") Long id){
        return userService.disable(id);
    }

    @GetMapping(value = "/{id}", consumes = "application/json")
    public UserDTO findById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public List<UserDTO> findByEnabledTrue(){
        return userService.findByEnabledTrue();
    }
    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public List<UserDTO> findByEnabledFalse(){
        return userService.findByEnabledFalse();
    }

    @GetMapping(consumes = "application/json")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }
}
