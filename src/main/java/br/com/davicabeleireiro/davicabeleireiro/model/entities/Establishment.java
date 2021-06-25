package br.com.davicabeleireiro.davicabeleireiro.model.entities;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.EstablishmentDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "establishment")
public class Establishment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String responsible;
    @Column(nullable = false)
    private String email;
    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "establishment_service", joinColumns = @JoinColumn(name = "establishment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Item> itemList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "establishment_contact", joinColumns = @JoinColumn(name = "establishment_id"),
        inverseJoinColumns = @JoinColumn(name = "contact_id"))
    private List<Contact> contactList = new ArrayList<>();



    public Establishment(){

    }

    public Establishment(EstablishmentDTO dto){
        id = dto.getId();
        name = dto.getName();
        description = dto.getDescription();
        responsible = dto.getResponsible();
        email = dto.getEmail();
        enabled = dto.getEnabled();
        address = dto.getAddress();
    }

    public Establishment(Long id, String name, String description, String responsible, String email, Boolean enabled, Address address) {
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
