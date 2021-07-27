package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.EstablishmentDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Establishment;
import br.com.davicabeleireiro.davicabeleireiro.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository repository;

    @Autowired
    private AddressGetFromAPI api;

    public EstablishmentDTO create(EstablishmentDTO dto){
        dto.setAddress(api.getAddressFromCEP(dto.getAddress().getCep()));
        return new EstablishmentDTO(repository.save(new Establishment(dto)));
    }

    public EstablishmentDTO findById(Long id){
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID "+id+" not found"));
        return convertToDTO(entity);
    }

    public Page<EstablishmentDTO> findAll(Pageable pageable){
        var entityList = repository.findAll(pageable);
        return entityList.map(this::convertToDTO);
    }

    private EstablishmentDTO convertToDTO(Establishment establishment){
        return new EstablishmentDTO(establishment);
    }
}
