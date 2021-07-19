package br.com.davicabeleireiro.davicabeleireiro.reservation;

import br.com.davicabeleireiro.davicabeleireiro.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReservationTest {

    @Autowired
    ReservationRepository repository;

    @Test
    public void updateReservation(){
        Long id = 4l;
        Date newDate = new Date();
        newDate.setTime(1626643800000l); //1626633000000

        var u = repository.checkAvailableReservationDateFromID(newDate, id);

        Assertions.assertEquals(newDate, u);

    }
}
