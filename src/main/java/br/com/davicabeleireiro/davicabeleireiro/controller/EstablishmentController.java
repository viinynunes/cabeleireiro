package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.EstablishmentDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    @Autowired
    private EstablishmentService service;

    @Autowired
    private PagedResourcesAssembler<EstablishmentDTO> assembler;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public EstablishmentDTO create(@RequestBody EstablishmentDTO dto){
        return service.create(dto);
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc")String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var establishmentPaged = service.findAll(pageable);

        PagedModel<?> model = assembler.toModel(establishmentPaged);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
