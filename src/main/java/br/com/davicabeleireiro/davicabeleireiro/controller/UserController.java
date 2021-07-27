package br.com.davicabeleireiro.davicabeleireiro.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.UserDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.UserService;
import br.com.davicabeleireiro.davicabeleireiro.utils.ControllerUtils;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PagedResourcesAssembler<UserDTO> assembler;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserDTO create(@RequestBody UserDTO dto) {
        var entity = userService.create(dto);
        entity.add(linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public UserDTO update(@PathVariable("id") Long id,
                          @RequestBody UserDTO dto) {
        dto.setId(id);
        var entity = userService.update(dto);

        entity.add(linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PatchMapping(value = "/disable/{id}", consumes = "application/json")
    public UserDTO disable(@PathVariable("id") Long id) {
        var entity = userService.disable(id);
        entity.add(linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}", consumes = "application/json")
    public UserDTO findById(@PathVariable("id") Long id) {
        var entity = userService.findById(id);
        entity.add(linkTo(methodOn(UserController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public ResponseEntity<?> findByEnabledTrue(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(ControllerUtils.getSortDirection(direction), "fullName"));

        var entityList = userService.findByEnabledTrue(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(UserController.class).findById(x.getId())).withSelfRel()));
        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public ResponseEntity<?> findByEnabledFalse(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(ControllerUtils.getSortDirection(direction), "fullName"));

        var entityList = userService.findByEnabledFalse(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(UserController.class).findById(x.getId())).withSelfRel()));
        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(ControllerUtils.getSortDirection(direction), "fullName"));
        var entityList = userService.findAll(pageable);

        entityList.forEach(x -> x.add(linkTo(methodOn(UserController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
