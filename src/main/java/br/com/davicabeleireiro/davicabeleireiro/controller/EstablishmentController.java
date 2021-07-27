package br.com.davicabeleireiro.davicabeleireiro.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        var entity = service.create(dto);
        entity.add(linkTo(methodOn(EstablishmentController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public EstablishmentDTO findById(@PathVariable("id") Long id){
        var entity = service.findById(id);
        entity.add(linkTo(methodOn(EstablishmentController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc")String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var entityList = service.findAll(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(EstablishmentController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
