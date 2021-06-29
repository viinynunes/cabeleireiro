package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.PermissionDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

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
    public List<PermissionDTO> findAll(){
        return permissionService.findAll();
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public List<PermissionDTO> findByEnabledTrue(){
        return permissionService.findByEnabledTrue();
    }

    @GetMapping(value = "findByEnabledFalse", consumes = "application/json")
    public List<PermissionDTO> findByEnabledFalse(){
        return permissionService.findByEnabledFalse();
    }

}
