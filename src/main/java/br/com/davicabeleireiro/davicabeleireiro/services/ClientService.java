package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.ClientDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.repository.ClientRepository;
import br.com.davicabeleireiro.davicabeleireiro.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var entity = repository.findByEmail(email);

        if (entity != null){
            return entity;
        }else {
            throw new UsernameNotFoundException("email " + email +" not found");
        }
    }

    public ClientDTO create(ClientDTO dto){

        verifyEmailExists(dto.getEmail());

        verifyPhoneExists(dto.getPhone());

        dto.setPassword(encoder.encode(dto.getPassword()));

        dto.setRoles(dto.getRoles());

        return new ClientDTO(repository.save(saveEntity(new Client(), dto)));
    }

    public ClientDTO update(ClientDTO dto){
        Client entity = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID " + dto.getId()+ " not found"));

        if (repository.verifyEmailAlreadyExists(dto.getEmail()) != null){
            if(repository.verifyEmailWithIDAlreadyExists(dto.getId(), dto.getEmail()) != null){
                return new ClientDTO(repository.save(saveEntity(entity, dto)));
            } else {
                throw new ResourceAlreadyExists("The email " + dto.getEmail() + " is already used");
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

    public List<ClientDTO> findByEveryAttribute(String param){
        var entityList = repository.findByEveryAttribute(param);

        List<ClientDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new ClientDTO(x)));
        return dtoList;
    }

    private void verifyEmailExists(String email){
         if (repository.verifyEmailAlreadyExists(email.toLowerCase()) != null){
             throw new ResourceAlreadyExists("The email " + email + " is already used");
         }
    }

    private void verifyPhoneExists(String phone){
        if (repository.verifyPhoneAlreadyExists(phone) != null){
            throw new ResourceAlreadyExists("The phone " + phone + " is already used");
        }
    }

    private Client saveEntity(Client entity, ClientDTO dto){
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.setPermissions(getPermissions(dto.getRoles()));
        return entity;
    }

    private List<Permission> getPermissions(List<String> roles){
        List<Permission> permissionList = new ArrayList<>();

        for (String role : roles){
            long id = Long.parseLong(role);
            permissionList.add(permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role " + id + " not found")));
        }
        return permissionList;
    }
}
