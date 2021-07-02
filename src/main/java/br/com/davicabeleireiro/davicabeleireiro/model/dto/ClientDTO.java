package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String password;

    private List<String> roles = new ArrayList<>();

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

        roles = client.getRoles();
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
