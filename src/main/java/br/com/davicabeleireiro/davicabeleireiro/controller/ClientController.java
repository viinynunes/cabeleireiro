package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ClientDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ClientDTO create(@RequestBody ClientDTO clientDTO){
        return service.create(clientDTO);
    }

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
}
