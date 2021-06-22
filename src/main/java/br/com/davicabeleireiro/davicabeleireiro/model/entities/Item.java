package br.com.davicabeleireiro.davicabeleireiro.model.entities;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "services")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private String duration;
    private String pathPicture;
    private Boolean enabled;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Item() {
    }

    public Item(Long id, String name, String description, Integer quantity, Double price, String duration, String pathPicture, Boolean enabled, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.duration = duration;
        this.pathPicture = pathPicture;
        this.enabled = enabled;
        this.category = category;
    }

    public Item(ItemDTO item){
        id = item.getId();
        name = item.getName();
        description = item.getDescription();
        quantity = item.getQuantity();
        price = item.getPrice();
        duration = item.getDuration();
        pathPicture = item.getPathPicture();
        enabled = item.getEnabled();
        category = item.getCategory();
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

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(quantity, item.quantity) && Objects.equals(price, item.price) && Objects.equals(duration, item.duration) && Objects.equals(pathPicture, item.pathPicture) && Objects.equals(enabled, item.enabled) && Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, quantity, price, duration, pathPicture, enabled, category);
    }
}
