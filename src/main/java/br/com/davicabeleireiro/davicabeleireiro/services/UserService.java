package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.UserDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import br.com.davicabeleireiro.davicabeleireiro.repository.PermissionRepository;
import br.com.davicabeleireiro.davicabeleireiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username);

        if (user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("username " + username + " not found");
        }
    }

    public UserDTO create(UserDTO dto){
        if (userRepository.findByUserName(dto.getUserName()) != null){
            throw new ResourceAlreadyExists("Username " + dto.getUserName() + " already exists");
        }

        verifyEmailAlreadyExists(dto.getEmail());

        User user = new User(dto);

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPermissions(getPermissionList(dto));

        return new UserDTO(userRepository.save(user));
    }

    public UserDTO update(UserDTO dto){
        var entity = userRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID "+ dto.getId()+" not found"));

        entity.setFullName(dto.getFullName());
        entity.setUserName(dto.getUserName());
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setAccountNonExpired(true);
        entity.setEnabled(true);

        entity.setPermissions(getPermissionList(dto));

        if (userRepository.findByUserName(dto.getUserName()) != null){
            if (userRepository.verifyUsernameWithIdAlreadyExists(dto.getUserName(), dto.getId()) != null){
                throw new ResourceAlreadyExists("Username " + dto.getUserName() + " already exists");
            }
        }

        if (userRepository.verifyEmailAlreadyExists(dto.getEmail()) != null){
            if (userRepository.verifyEmailWithIDAlreadyExists(dto.getFullName(), dto.getId()) != null){
                return new UserDTO(userRepository.save(entity));
            }else {
                throw new ResourceAlreadyExists("The email " + dto.getEmail() + " is already used");
            }
        }else {
            return new UserDTO(userRepository.save(entity));
        }
    }

    @Transactional
    public UserDTO disable(Long id){
        userRepository.disable(id);

        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID "+ id +" not found"));

        return new UserDTO(entity);
    }

    public UserDTO findById(Long id){
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID "+ id +" not found"));
        return new UserDTO(entity);
    }

    public List<UserDTO> findAll(){
        var entityList = userRepository.findAll();

        List<UserDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new UserDTO(x)));
        return dtoList;
    }

    public List<UserDTO> findByEnabledTrue(){
        var entityList = userRepository.findByEnabledTrue();

        List<UserDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new UserDTO(x)));
        return dtoList;
    }

    public List<UserDTO> findByEnabledFalse(){
        var entityList = userRepository.findByEnabledFalse();

        List<UserDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new UserDTO(x)));
        return dtoList;
    }

    private List<Permission> getPermissionList(UserDTO dto){
        List<Permission> permissionList = new ArrayList<>();
        for (String role : dto.getRoles()){
            permissionList.add(permissionRepository.findById(Long.parseLong(role)).orElseThrow(() ->
                    new ResourceNotFoundException("Permission " + role + " not found")));
        }

        return permissionList;
    }

    private void verifyEmailAlreadyExists(String email){
        if (userRepository.verifyEmailAlreadyExists(email) != null){
            throw new ResourceAlreadyExists("Email " + email + " already exists");
        }
    }
}
