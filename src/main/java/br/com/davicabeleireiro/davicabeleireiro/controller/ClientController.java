package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.UserDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private UserService userService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public UserDTO create(@RequestBody UserDTO dto) {
        dto.setRoles(getClientRole());
        return userService.create(dto);
    }

    /*
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ClientDTO update(@PathVariable("id") Long id,
            @RequestBody ClientDTO dto){
        dto.setId(id);
        return service.update(dto);
    }

    @GetMapping(consumes = "application/json")
    public List<ClientDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/findByName/{name}", consumes = "application/json")
    public List<ClientDTO> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

    @GetMapping(value = "/findByEveryAttribute/{param}", consumes = "application/json")
    public List<ClientDTO> findByEveryAttribute(@PathVariable("param") String param){
        return service.findByEveryAttribute(param);
    }

     */

    public List<String> getClientRole() {
        List<String> role = new ArrayList<>();
        role.add("Client");
        return role;
    }
}
