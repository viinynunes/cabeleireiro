package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Reservation;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByEnabledTrue();

    List<Reservation> findByEnabledFalse();

    @Modifying
    @Query("UPDATE Reservation r SET r.enabled = false WHERE r.id =:id")
    void disableReservation(@Param("id") Long id);

    @Query("SELECT r FROM Reservation r INNER JOIN r.user u WHERE u.userName = :username")
    List<Reservation> findByUser(@Param("username") String username);

    @Query("SELECT r FROM Reservation r INNER JOIN r.user u WHERE u.id = :id")
    List<Reservation> findByUserID(@Param("id") Long id);

    @Query("SELECT r.scheduleDate FROM Reservation r WHERE r.scheduleDate = :newDate AND r.enabled = true")
    Date checkAvailableReservationDate(@Param("newDate") Date date);

    @Query("SELECT r FROM Reservation r INNER JOIN r.user u on user_id=u.id WHERE u.id = :id " +
            "AND r.scheduleDate = :newDate AND r.enabled = true")
    Reservation checkAvailableReservationDateFromID(@Param("newDate")Date date, @Param("id") Long id);



}
