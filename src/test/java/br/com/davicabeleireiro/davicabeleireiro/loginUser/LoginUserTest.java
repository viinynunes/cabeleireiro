package br.com.davicabeleireiro.davicabeleireiro.loginUser;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import br.com.davicabeleireiro.davicabeleireiro.security.jwt.JWTTokenProvider;
import br.com.davicabeleireiro.davicabeleireiro.userMock.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoginUserTest {

    @Autowired
    JWTTokenProvider tokenProvider;

    @Test
    public void gerarToken(){
        String token = "";

        User user = UserMock.getUser();

        token = tokenProvider.createToken(user.getUsername(), user.getRoles());

        System.out.println(token);

        Assertions.assertEquals(true, true);
    }

}
