package br.com.davicabeleireiro.davicabeleireiro.controller;

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
        return service.create(dto);
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ReservationDTO update(@PathVariable("id") Long id,
                                 @RequestBody ReservationDTO dto) {
        dto.setId(id);
        return service.update(dto);
    }

    @GetMapping(consumes = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityPaged = service.findAll(pageable);

        PagedModel<?> model = assembler.toModel(entityPaged);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public ResponseEntity<?> findByEnabledTrue(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityPaged = service.findByEnabledTrue(pageable);

        PagedModel<?> model = assembler.toModel(entityPaged);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public ResponseEntity<?> findByEnabledFalse(@RequestParam(value = "direction", defaultValue = "desc") String direction,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {

        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityPaged = service.findByEnabledFalse(pageable);

        PagedModel<?> model = assembler.toModel(entityPaged);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByUser/{username}", consumes = "application/json")
    public ResponseEntity<?> findByUser(@PathVariable("username") String username,
                                        @RequestParam(value = "direction", defaultValue = "desc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityPaged = service.findByUser(username, pageable);

        PagedModel<?> model = assembler.toModel(entityPaged);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "/findByUserId/{id}", consumes = "application/json")
    public ResponseEntity<?> findByUser(@PathVariable("id") Long id,
                                        @RequestParam(value = "direction", defaultValue = "desc") String direction,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size){
        var sortDirection = ControllerUtils.getSortDirection(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "scheduleDate"));

        var entityPaged = service.findByUserId(id, pageable);

        PagedModel<?> model = assembler.toModel(entityPaged);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ReservationDTO disableReservation(@PathVariable("id") Long id) {
        return service.disableReservation(id);
    }
}
