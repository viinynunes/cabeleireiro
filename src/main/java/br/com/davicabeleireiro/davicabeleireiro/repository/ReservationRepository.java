package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query
    List<Reservation> findByEnabledTrue();

    @Modifying
    @Query("UPDATE Reservation r SET r.enabled = false WHERE r.id =:id")
    void disableReservation(@Param("id") Long id);
}
