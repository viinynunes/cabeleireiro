package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Address;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Contact;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Establishment;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EstablishmentDTO extends RepresentationModel<EstablishmentDTO> implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String responsible;
    private String email;
    private Boolean enabled;

    private Address address;

    private List<Item> itemList = new ArrayList<>();

    private List<Contact> contactList = new ArrayList<>();

    public EstablishmentDTO(){

    }

    public EstablishmentDTO(Establishment establishment){
        id = establishment.getId();
        name = establishment.getName();
        description = establishment.getDescription();
        responsible = establishment.getResponsible();
        email = establishment.getEmail();
        enabled = establishment.getEnabled();
        address = establishment.getAddress();
    }

    public EstablishmentDTO(Long id, String name, String description, String responsible, String email, Boolean enabled, Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.responsible = responsible;
        this.email = email;
        this.enabled = enabled;
        this.address = address;
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

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void addContact(Contact contact) {
        this.contactList.add(contact);
    }

    public void removeContact(Contact contact) {
        this.contactList.remove(contact);
    }

    public void addContact(Item item) {
        this.itemList.add(item);
    }

    public void removeContact(Item item) {
        this.itemList.remove(item);
    }
}
