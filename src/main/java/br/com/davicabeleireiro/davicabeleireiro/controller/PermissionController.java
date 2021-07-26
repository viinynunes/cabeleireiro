package br.com.davicabeleireiro.davicabeleireiro.controller;

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
        return permissionService.create(dto);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public PermissionDTO update(@PathVariable("id") Long id,
                                @RequestBody PermissionDTO dto){
        dto.setId(id);
        return permissionService.update(dto);
    }

    @PatchMapping(value = "/disable/{id}", consumes = "application/json")
    public PermissionDTO disable(@PathVariable("id") Long id){
        return permissionService.disable(id);
    }

    @GetMapping(value = "/{id}", consumes = "application/json")
    public PermissionDTO findById(@PathVariable("id") Long id){
        return permissionService.findById(id);
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        var permissionPaged = permissionService.findAll(pageable);

        PagedModel<?> model = assembler.toModel(permissionPaged);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public ResponseEntity<?> findByEnabledTrue(@RequestParam(value = "direction", defaultValue = "desc")String direction,
                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        var permissionPaged = permissionService.findByEnabledTrue(pageable);

        PagedModel<?> model = assembler.toModel(permissionPaged);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "findByEnabledFalse", consumes = "application/json")
    public ResponseEntity<?> findByEnabledFalse(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "description"));

        var permissionPaged = permissionService.findByEnabledFalse(pageable);

        PagedModel<?> model = assembler.toModel(permissionPaged);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
