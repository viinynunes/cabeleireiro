package br.com.davicabeleireiro.davicabeleireiro.controller;

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
        return service.create(dto);
    }

    @GetMapping(produces = "application/json" ,consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc")String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        var itemPaged = service.findAll(pageable);

        PagedModel<?> model = assembler.toModel(itemPaged);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
