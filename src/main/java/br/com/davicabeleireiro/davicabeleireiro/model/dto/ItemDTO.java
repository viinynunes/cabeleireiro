package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;

import java.io.Serializable;

public class ItemDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private String duration;
    private String pathPicture;
    private Boolean enabled;

    private Category category;

    public ItemDTO() {
    }

    public ItemDTO(Long id, String name, String description, Integer quantity, Double price, String duration, String pathPicture, Boolean enabled, Category category) {
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

    public ItemDTO(Item item){
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
}
