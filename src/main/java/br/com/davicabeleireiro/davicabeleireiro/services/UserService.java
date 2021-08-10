package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.UserDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import br.com.davicabeleireiro.davicabeleireiro.repository.PermissionRepository;
import br.com.davicabeleireiro.davicabeleireiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("user")
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

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("username " + username + " not found");
        }
    }

    public UserDTO create(UserDTO dto) {
        if (userRepository.findByUserName(dto.getUserName()) != null) {
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

    public UserDTO update(UserDTO dto) {
        var entity = userRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID " + dto.getId() + " not found"));

        entity.setFullName(dto.getFullName());
        entity.setUserName(dto.getUserName());
        entity.setPassword(encoder.encode(dto.getPassword()));
        entity.setCredentialsNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setAccountNonExpired(true);
        entity.setEnabled(true);

        entity.setPermissions(getPermissionList(dto));

        if (userRepository.findByUserName(dto.getUserName()) != null) {
            if (userRepository.verifyUsernameWithIdAlreadyExists(dto.getUserName(), dto.getId()) == null) {
                throw new ResourceAlreadyExists("The username " + dto.getUserName() + " is already used");
            }
        }

        if (userRepository.verifyEmailAlreadyExists(dto.getEmail()) != null) {
            if (userRepository.verifyEmailWithIDAlreadyExists(dto.getEmail(), dto.getId()) != null) {
                return new UserDTO(userRepository.save(entity));
            } else {
                throw new ResourceAlreadyExists("The email " + dto.getEmail() + " is already used");
            }
        } else {
            return new UserDTO(userRepository.save(entity));
        }
    }

    @Transactional
    public UserDTO disable(Long id) {
        userRepository.disable(id);

        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " not found"));

        return new UserDTO(entity);
    }

    public UserDTO findById(Long id) {
        var entity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " not found"));
        return new UserDTO(entity);
    }

    public String findFullNameByUserName(String username){
        var fullName = userRepository.findFullNameByUserName(username);

        if (fullName == null){
            throw new ResourceNotFoundException("username " + username + "not found");
        }else {
            return fullName;
        }
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        var entityList = userRepository.findAll(pageable);

        return entityList.map(this::convertToDTO);
    }

    public Page<UserDTO> findByEnabledTrue(Pageable pageable) {
        var entityList = userRepository.findByEnabledTrue(pageable);

        return entityList.map(this::convertToDTO);
    }

    public Page<UserDTO> findByEnabledFalse(Pageable pageable) {
        var entityList = userRepository.findByEnabledFalse(pageable);

        return entityList.map(this::convertToDTO);
    }

    private List<Permission> getPermissionList(UserDTO dto) {
        List<Permission> permissionList = new ArrayList<>();
        for (String role : dto.getRoles()) {
            var permission = permissionRepository.findByDescription(role.toUpperCase());
            if (permission != null) {
                permissionList.add(permission);
            } else {
                throw new ResourceNotFoundException("Permission " + role + " not found");
            }
        }

        return permissionList;
    }

    private void verifyEmailAlreadyExists(String email) {
        if (userRepository.verifyEmailAlreadyExists(email) != null) {
            throw new ResourceAlreadyExists("Email " + email + " already exists");
        }
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user);
    }
}
