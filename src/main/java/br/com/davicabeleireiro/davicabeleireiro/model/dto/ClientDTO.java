package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;

public class ClientDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String password;

    public ClientDTO(){

    }

    public ClientDTO(Long id, String fullName, String phone, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public ClientDTO(Client client){
        id = client.getId();
        fullName = client.getFullName();
        phone = client.getPhone();
        email = client.getEmail();
        password = client.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
