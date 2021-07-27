package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.AddressDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Address;
import br.com.davicabeleireiro.davicabeleireiro.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressGetFromAPI api;

    public AddressDTO create(AddressDTO dto){
        return new AddressDTO(repository.save(new Address(dto)));
    }

    public AddressDTO update(AddressDTO dto){
        var entity = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID " + dto.getId() + " not found"));

        entity.setCep(dto.getCep());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setLocalidade(dto.getLocalidade());
        entity.setUf(dto.getUf());
        entity.setIbge(dto.getIbge());
        entity.setGia(dto.getGia());
        entity.setDdd(dto.getDdd());
        entity.setSiafi(dto.getSiafi());

        return new AddressDTO(repository.save(entity));
    }

    public AddressDTO findById(Long id){
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID "+id+" not found"));
        return convertToDTO(entity);
    }

    public Page<AddressDTO> findAll(Pageable pageable){
        var entityList = repository.findAll(pageable);

        return entityList.map(this::convertToDTO);
    }

    public AddressDTO convertToDTO(Address address){
        return new AddressDTO(address);
    }

}
