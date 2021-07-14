package br.com.davicabeleireiro.davicabeleireiro.controller;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ReservationDTO;
import br.com.davicabeleireiro.davicabeleireiro.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

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
    public List<ReservationDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/findByEnabledTrue", consumes = "application/json")
    public List<ReservationDTO> findByEnabledTrue() {
        return service.findByEnabledTrue();
    }

    @GetMapping(value = "/findByEnabledFalse", consumes = "application/json")
    public List<ReservationDTO> findByEnabledFalse() {
        return service.findByEnabledFalse();
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ReservationDTO disableReservation(@PathVariable("id") Long id) {
        return service.disableReservation(id);
    }
}
