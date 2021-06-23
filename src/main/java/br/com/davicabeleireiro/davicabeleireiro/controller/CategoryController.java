package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.CategoryDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public CategoryDTO create(@RequestBody CategoryDTO dto){
        return service.create(dto);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public CategoryDTO update(@PathVariable("id") Long id, @RequestBody CategoryDTO dto){
        dto.setId(id);
        return service.update(dto);
    }

    @PatchMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public CategoryDTO disable(@PathVariable("id") Long id){
        return service.disable(id);
    }

    @GetMapping(consumes = "application/json")
    public List<CategoryDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{name}", consumes = "application/json")
    public List<CategoryDTO> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public List<CategoryDTO> findByEnabledTrue(){
        return service.findByEnabledTrue();
    }

    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public List<CategoryDTO> findByEnabledFalse(){
        return service.findByEnabledFalse();
    }
}
