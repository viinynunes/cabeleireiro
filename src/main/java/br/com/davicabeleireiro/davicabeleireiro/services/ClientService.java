package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.EmailAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.ClientDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import br.com.davicabeleireiro.davicabeleireiro.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ClientDTO create(ClientDTO dto){

        verifyEmailExists(dto.getEmail());

        dto.setPassword(encoder.encode(dto.getPassword()));

        return new ClientDTO(repository.save(new Client(dto)));
    }

    public ClientDTO update(ClientDTO dto){
        Client entity = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID " + dto.getId()+ " not found"));

        if (repository.verifyEmailAlreadyExists(dto.getEmail()) != null){
            if(repository.verifyEmailWithIDAlreadyExists(dto.getId(), dto.getEmail()) != null){
                return new ClientDTO(repository.save(saveEntity(entity, dto)));
            } else {
                throw new EmailAlreadyExists("The email " + dto.getEmail() + " is already used");
            }
        } else {
            return new ClientDTO(repository.save(saveEntity(entity, dto)));
        }
    }

    public List<ClientDTO> findAll(){
        var entityList = repository.findAll();
        List<ClientDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new ClientDTO(x)));
        return dtoList;
    }

    public List<ClientDTO> findByName(String name){
        var entityList = repository.findByFullName(name);
        List<ClientDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new ClientDTO(x)));
        return dtoList;
    }

    private void verifyEmailExists(String email){
         if (repository.verifyEmailAlreadyExists(email.toLowerCase()) != null){
             throw new EmailAlreadyExists("The email " + email + " is already used");
         }
    }

    private Client saveEntity(Client entity, ClientDTO dto){
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(encoder.encode(dto.getPassword()));

        return entity;
    }
}
