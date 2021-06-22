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

    @GetMapping(consumes = "application/json")
    public List<CategoryDTO> findAll(){
        return service.findAll();
    }
}
