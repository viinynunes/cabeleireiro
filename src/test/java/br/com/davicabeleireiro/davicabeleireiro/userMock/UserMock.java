package br.com.davicabeleireiro.davicabeleireiro.userMock;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserMock {

    public static User getUser(){
        Permission permission = new Permission();
        permission.setId(2l);
        permission.setEnabled(true);
        permission.setDescription("ADMIN");

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);

        User user = new User();
        user.setId(1l);
        user.setFullName("Vinicius Nunes");
        user.setUserName("nunesV");
        user.setEmail("nunes@hotmail.com");
        user.setPassword("nunes123");
        user.setPermissions(permissionList);

        return user;
    }


}
