package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ContactDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ContactDTO create(@RequestBody ContactDTO dto){
        return service.create(dto);
    }

    @GetMapping(consumes = "application/json")
    public List<ContactDTO> findAll(){
        return service.findAll();
    }
}
