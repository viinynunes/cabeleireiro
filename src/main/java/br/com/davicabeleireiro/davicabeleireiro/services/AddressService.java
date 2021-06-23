package br.com.davicabeleireiro.davicabeleireiro.services;

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
        Address address = api.getAddressFromCEP(dto.getCep());

        return new AddressDTO(repository.save(address));
    }

    public List<AddressDTO> findAll(){
        var list = repository.findAll();
        List<AddressDTO> listDTO = new ArrayList<>();
        list.forEach(x -> listDTO.add(new AddressDTO(x)));
        return listDTO;
    }


}
