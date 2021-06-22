package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c.email FROM Client c WHERE LOWER(c.email) = :email")
    String verifyEmailAlreadyExists(@PathVariable("email") String email);
}
