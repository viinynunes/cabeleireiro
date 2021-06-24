package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.AddressDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Address;
import br.com.davicabeleireiro.davicabeleireiro.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<AddressDTO> findAll(){
        var list = repository.findAll();
        List<AddressDTO> listDTO = new ArrayList<>();
        list.forEach(x -> listDTO.add(new AddressDTO(x)));
        return listDTO;
    }


}
