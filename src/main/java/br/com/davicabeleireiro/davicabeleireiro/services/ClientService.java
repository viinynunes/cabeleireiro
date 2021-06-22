package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ClientDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import br.com.davicabeleireiro.davicabeleireiro.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public ClientDTO create(ClientDTO dto){
        return new ClientDTO(repository.save(new Client(dto)));
    }

    public List<ClientDTO> findAll(){
        var entityList = repository.findAll();
        List<ClientDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new ClientDTO(x)));
        return dtoList;
    }
}
