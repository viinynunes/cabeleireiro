package br.com.davicabeleireiro.davicabeleireiro.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ReservationDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.ReservationService;
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
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private PagedResourcesAssembler<ReservationDTO> assembler;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ReservationDTO create(@RequestBody ReservationDTO dto) {
        var entity = service.create(dto);
        entity.add(linkTo(methodOn(ReservationController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ReservationDTO update(@PathVariable("id") Long id,
                                 @RequestBody ReservationDTO dto) {
        dto.setId(id);
        var entity = service.update(dto);
        entity.add(linkTo(methodOn(ReservationController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ReservationDTO findById(@PathVariable("id") Long id){
        var entity = service.findById(id);
        entity.add(linkTo(methodOn(ReservationController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityList = service.findAll(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(ReservationController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public ResponseEntity<?> findByEnabledTrue(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityList = service.findByEnabledTrue(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(ReservationController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public ResponseEntity<?> findByEnabledFalse(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityList = service.findByEnabledFalse(pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(ReservationController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByUser/{username}", consumes = "application/json")
    public ResponseEntity<?> findByUser(@PathVariable("username") String username,
                                        @RequestParam(value = "direction", defaultValue = "desc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityList = service.findByUser(username, pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(ReservationController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByUserId/{id}", consumes = "application/json")
    public ResponseEntity<?> findByUser(@PathVariable("id") Long id,
                                        @RequestParam(value = "direction", defaultValue = "desc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityList = service.findByUserId(id, pageable);
        entityList.forEach(x -> x.add(linkTo(methodOn(ReservationController.class).findById(x.getId())).withSelfRel()));

        PagedModel<?> model = assembler.toModel(entityList);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ReservationDTO disableReservation(@PathVariable("id") Long id) {
        var entity = service.disableReservation(id);
        entity.add(linkTo(methodOn(ReservationController.class).findById(entity.getId())).withSelfRel());
        return entity;
    }
}
