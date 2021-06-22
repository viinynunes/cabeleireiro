package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.EstablishmentDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Establishment;
import br.com.davicabeleireiro.davicabeleireiro.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<EstablishmentDTO> findAll(){
        List<Establishment> list = repository.findAll();
        List<EstablishmentDTO> dtoList = new ArrayList<>();
        list.forEach(x -> dtoList.add(new EstablishmentDTO(x)));
        return dtoList;
    }
}
