package br.com.davicabeleireiro.davicabeleireiro.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ItemDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.ItemService;
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
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @Autowired
    private PagedResourcesAssembler<ItemDTO> assembler;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ItemDTO create(@RequestBody ItemDTO dto){
        var entity = service.create(dto);
        entity.add(linkTo(methodOn(ItemController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ItemDTO findById(@PathVariable("id") Long id){
        var entity = service.findById(id);
        entity.add(linkTo(methodOn(ItemController.class).findById(id)).withSelfRel());
        return entity;
    }

    @GetMapping(produces = "application/json" ,consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc")String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var entityList = service.findAll(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(ItemController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
