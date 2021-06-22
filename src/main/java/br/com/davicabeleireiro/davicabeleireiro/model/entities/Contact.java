package br.com.davicabeleireiro.davicabeleireiro.model.entities;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ContactDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 14)
    private String phone;

    public Contact(){}

    public Contact(Long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public Contact(ContactDTO dto){
        id = dto.getId();
        phone = dto.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
