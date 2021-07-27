package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.AddressDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.AddressService;
import br.com.davicabeleireiro.davicabeleireiro.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @Autowired
    private PagedResourcesAssembler<AddressDTO> assembler;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public AddressDTO create(@RequestBody AddressDTO dto){
        var entity = service.create(dto);
        entity.add(linkTo(methodOn(AddressController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public AddressDTO update(@PathVariable("id") Long id, @RequestBody AddressDTO dto){
        dto.setId(id);
        var entity = service.update(dto);
        entity.add(linkTo(methodOn(AddressController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public AddressDTO findById(@PathVariable("id") Long id){
        var entity = service.findById(id);
        entity.add(linkTo(methodOn(AddressController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc")String direction,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(ControllerUtils.getSortDirection(direction), "logradouro"));
        var entityList = service.findAll(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(AddressController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
