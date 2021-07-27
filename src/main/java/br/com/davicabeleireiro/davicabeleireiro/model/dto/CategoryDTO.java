package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryDTO extends RepresentationModel<CategoryDTO> implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Boolean enabled;

    @JsonIgnore
    private List<Item> itemList = new ArrayList<>();

    public CategoryDTO(){}

    public CategoryDTO(Long id, String name, String description, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    public CategoryDTO(Category category){
        id = category.getId();
        name = category.getName();
        description = category.getDescription();
        enabled = category.getEnabled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setServicesList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getServicesList() {
        return itemList;
    }

    public void addSericeList(Item service){
        this.itemList.add(service);
    }

    public void removeSericeList(Item service){
        this.itemList.remove(service);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(enabled, that.enabled) && Objects.equals(itemList, that.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, enabled, itemList);
    }
}
