package br.com.davicabeleireiro.davicabeleireiro.model.entities;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ClientDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String fullName;
    @Column(length = 15)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 80, nullable = false)
    private String password;

    public Client(){

    }

    public Client(Long id, String fullName, String phone, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Client(ClientDTO dto){
        id = dto.getId();
        fullName = dto.getFullName();
        phone = dto.getPhone();
        email = dto.getEmail();
        password = dto.getPassword();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(fullName, client.fullName) && Objects.equals(phone, client.phone) && Objects.equals(email, client.email) && Objects.equals(password, client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, phone, email, password);
    }
}
