package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Contact;

import javax.persistence.Column;

public class ContactDTO {

    private Long id;
    private String phone;

    public ContactDTO(){}

    public ContactDTO(Long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public ContactDTO(Contact contact){
        id = contact.getId();
        phone = contact.getPhone();
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
