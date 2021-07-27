package br.com.davicabeleireiro.davicabeleireiro.model.dto;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {

    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;

    private List<String> roles;

    public UserDTO(){}

    public UserDTO(Long id, String fullName, String userName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public UserDTO(User user){
        id = user.getId();
        fullName = user.getFullName();
        userName = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
