package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ItemDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ItemDTO create(@RequestBody ItemDTO dto){
        return service.create(dto);
    }

    @GetMapping(produces = "application/json" ,consumes = "application/json")
    public List<ItemDTO> findAll(){
        return service.findAll();
    }
}
