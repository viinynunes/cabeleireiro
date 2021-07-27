package br.com.davicabeleireiro.davicabeleireiro.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.PermissionDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PagedResourcesAssembler<PermissionDTO> assembler;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public PermissionDTO create(@RequestBody PermissionDTO dto){
        var entity = permissionService.create(dto);
        entity.add(linkTo(methodOn(PermissionController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public PermissionDTO update(@PathVariable("id") Long id,
                                @RequestBody PermissionDTO dto){
        dto.setId(id);
        var entity = permissionService.update(dto);
        entity.add(linkTo(methodOn(PermissionController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PatchMapping(value = "/disable/{id}", consumes = "application/json")
    public PermissionDTO disable(@PathVariable("id") Long id){
        var entity = permissionService.disable(id);
        entity.add(linkTo(methodOn(PermissionController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}", consumes = "application/json")
    public PermissionDTO findById(@PathVariable("id") Long id){
        var entity = permissionService.findById(id);
        entity.add(linkTo(methodOn(PermissionController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        var entityList = permissionService.findAll(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(PermissionController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public ResponseEntity<?> findByEnabledTrue(@RequestParam(value = "direction", defaultValue = "desc")String direction,
                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        var entityList = permissionService.findByEnabledTrue(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(PermissionController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "findByEnabledFalse", consumes = "application/json")
    public ResponseEntity<?> findByEnabledFalse(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        var entityList = permissionService.findByEnabledFalse(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(PermissionController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
