package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.PermissionDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<PermissionDTO> findAll(){
        var entityList = permissionRepository.findAll();

        List<PermissionDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new PermissionDTO(x)));
        return dtoList;
    }

    public List<PermissionDTO> findByEnabledTrue(){
        var entityList = permissionRepository.findByEnabledTrue();

        List<PermissionDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new PermissionDTO(x)));
        return dtoList;
    }

    public List<PermissionDTO> findByEnabledFalse(){
        var entityList = permissionRepository.findByEnabledFalse();

        List<PermissionDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new PermissionDTO(x)));
        return dtoList;
    }


}
