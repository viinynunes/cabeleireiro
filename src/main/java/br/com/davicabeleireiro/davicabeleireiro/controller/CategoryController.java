package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.CategoryDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private PagedResourcesAssembler<CategoryDTO> assembler;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return service.create(dto);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public CategoryDTO update(@PathVariable("id") Long id, @RequestBody CategoryDTO dto) {
        dto.setId(id);
        return service.update(dto);
    }

    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public CategoryDTO disable(@PathVariable("id") Long id) {
        return service.disable(id);
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "asc") String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<CategoryDTO> categoryPaged = service.findAll(pageable);

        PagedModel<?> model = assembler.toModel(categoryPaged);

        return new ResponseEntity<>(model, HttpStatus.OK);

    }

    @GetMapping(value = "/{name}", consumes = "application/json")
    public ResponseEntity<?> findByName(@PathVariable("name") String name,
                                        @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var categoryPaged = service.findByName(name, pageable);

        PagedModel<?> model = assembler.toModel(categoryPaged);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public ResponseEntity<?> findByEnabledTrue(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var categoryPaged = service.findByEnabledTrue(pageable);

        PagedModel<?> model = assembler.toModel(categoryPaged);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public ResponseEntity<?> findByEnabledFalse(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var categoryPaged = service.findByEnabledFalse(pageable);

        PagedModel<?> model = assembler.toModel(categoryPaged);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
