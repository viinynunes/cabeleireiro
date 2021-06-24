package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.AddressDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public AddressDTO create(@RequestBody AddressDTO dto){
        return service.create(dto);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public AddressDTO update(@PathVariable("id") Long id, @RequestBody AddressDTO dto){
        dto.setId(id);
        return service.update(dto);
    }

    @GetMapping(produces = "application/json", consumes = "application/json")
    public List<AddressDTO> findAll(){
        return service.findAll();
    }
}
