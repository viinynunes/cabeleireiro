package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.CategoryDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private PagedResourcesAssembler<CategoryDTO> assembler;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        var entity = service.create(dto);
        entity.add(linkTo(methodOn(EstablishmentController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public CategoryDTO update(@PathVariable("id") Long id, @RequestBody CategoryDTO dto) {
        dto.setId(id);
        var entity = service.update(dto);
        entity.add(linkTo(methodOn(EstablishmentController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public CategoryDTO disable(@PathVariable("id") Long id) {
        var entity = service.disable(id);
        entity.add(linkTo(methodOn(EstablishmentController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public CategoryDTO findById(@PathVariable("id") Long id){
        var entity = service.findById(id);
        entity.add(linkTo(methodOn(CategoryController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "asc") String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var entityList = service.findAll(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(EstablishmentController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @GetMapping(value = "/{name}", consumes = "application/json")
    public ResponseEntity<?> findByName(@PathVariable("name") String name,
                                        @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var entityList = service.findByName(name, pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(EstablishmentController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public ResponseEntity<?> findByEnabledTrue(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var entityList = service.findByEnabledTrue(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(EstablishmentController.class).findById(x.getId())).withSelfRel()));
        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public ResponseEntity<?> findByEnabledFalse(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var entityList = service.findByEnabledFalse(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(EstablishmentController.class).findById(x.getId())).withSelfRel()));
        PagedModel<?> model = assembler.toModel(entityList);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
