package br.com.davicabeleireiro.davicabeleireiro.userTest;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceAlreadyExists;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.UserDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import br.com.davicabeleireiro.davicabeleireiro.repository.UserRepository;
import br.com.davicabeleireiro.davicabeleireiro.userMock.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void verifyEmailWithID(){
        Long id = 2l;
        String email = "davi_P@hotmail.com";
        String username = "daviP";

        var entity = repository.findById(2l).get();

        if (repository.findByUserName(username) != null){
            if (repository.verifyUsernameWithIdAlreadyExists(username, id) != null){
                Assertions.assertEquals(true, true);
            }else {
                Assertions.assertEquals(true, false);
            }
        }else {
            Assertions.assertEquals(true, true);
        }


        /*

        if (repository.verifyEmailAlreadyExists(email) != null){
            if (repository.verifyEmailWithIDAlreadyExists(email, id) != null){
                Assertions.assertEquals(true, true);
            }else {
                Assertions.assertEquals(true, false);            }
        }else {
            Assertions.assertEquals(true, true);
        }
        */
    }

}
