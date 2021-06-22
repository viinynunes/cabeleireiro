package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.EstablishmentDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    @Autowired
    private EstablishmentService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public EstablishmentDTO create(@RequestBody EstablishmentDTO dto){


        return service.create(dto);
    }

    @GetMapping(consumes = "application/json")
    public List<EstablishmentDTO> findAll(){
        return service.findAll();
    }
}
