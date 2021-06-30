package br.com.davicabeleireiro.davicabeleireiro.userMock;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserMock {

    public static User createUser(){
        Permission permission = new Permission();
        permission.setId(2l);
        permission.setEnabled(true);
        permission.setDescription("CLIENT");

        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(permission);

        User user = new User();
        user.setId(2l);
        user.setFullName("Davi Silva");
        user.setUserName("daviP");
        user.setEmail("davi_P@hotmail.com");
        user.setPassword("davi123");
        user.setPermissions(permissionList);

        return user;
    }


}
