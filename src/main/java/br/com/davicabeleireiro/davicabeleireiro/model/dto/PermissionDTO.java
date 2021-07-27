package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class PermissionDTO extends RepresentationModel<PermissionDTO> implements Serializable {

    private Long id;
    private String description;
    private Boolean enabled;

    public PermissionDTO(){}

    public PermissionDTO(Long id, String description, Boolean enabled) {
        this.id = id;
        this.description = description;
        this.enabled = enabled;
    }

    public PermissionDTO(Permission permission){
        id = permission.getId();
        description = permission.getDescription();
        enabled = permission.getEnabled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
