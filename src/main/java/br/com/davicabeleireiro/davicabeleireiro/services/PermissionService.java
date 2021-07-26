package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.PermissionDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionDTO create(PermissionDTO dto){
        var description = permissionRepository.findByDescription(dto.getDescription().toUpperCase());

        if (description != null){
            throw new ResourceAlreadyExists("Description " + description + " already exists");
        }else {
            dto.setDescription(dto.getDescription().toUpperCase());
            dto.setEnabled(true);
            return new PermissionDTO(permissionRepository.save(new Permission(dto)));
        }
    }

    public PermissionDTO update(PermissionDTO dto){

        if (permissionRepository.findByDescription(dto.getDescription()) != null){
            throw new ResourceAlreadyExists("Description " + dto.getDescription() + " already exists");
        }
        var entity = permissionRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID " + dto.getId() + " not found"));

        entity.setDescription(dto.getDescription().toUpperCase());
        entity.setEnabled(true);

        return new PermissionDTO(permissionRepository.save(entity));
    }

    @Transactional
    public PermissionDTO disable(Long id){
        permissionRepository.disable(id);

        var entity = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " not found"));

        return new PermissionDTO(entity);
    }

    public PermissionDTO findById(Long id){
        var entity = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " not found"));

        return new PermissionDTO(entity);
    }

    public Page<PermissionDTO> findAll(Pageable pageable){
        var entityList = permissionRepository.findAll(pageable);

        return entityList.map(this::convertToDTO);
    }

    public Page<PermissionDTO> findByEnabledTrue(Pageable pageable){
        var entityList = permissionRepository.findByEnabledTrue(pageable);

        return entityList.map(this::convertToDTO);
    }

    public Page<PermissionDTO> findByEnabledFalse(Pageable pageable){
        var entityList = permissionRepository.findByEnabledFalse(pageable);

        return entityList.map(this::convertToDTO);
    }

    private PermissionDTO convertToDTO(Permission permission){
        return new PermissionDTO(permission);
    }

}
