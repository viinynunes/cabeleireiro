package br.com.davicabeleireiro.davicabeleireiro.model.entities;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean enabled;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Item> itemList = new ArrayList<>();

    public Category(){}

    public Category(Long id, String name, String description, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    public Category(CategoryDTO dto){
        id = dto.getId();
        name = dto.getName();
        description = dto.getDescription();
        enabled = dto.getEnabled();
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
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && Objects.equals(description, category.description) && Objects.equals(enabled, category.enabled) && Objects.equals(itemList, category.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, enabled, itemList);
    }
}
