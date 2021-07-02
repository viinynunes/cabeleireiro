package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    @Query("SELECT c.phone FROM Client c WHERE c.phone = :phone")
    String verifyPhoneAlreadyExists(@Param("phone") String phone);

    @Query("SELECT c FROM Client c WHERE c.id = :id AND c.phone = :phone")
    Client verifyPhoneWithIDAlreadyExists(@Param("phone") String phone, @Param("id") Long id);

    @Query("SELECT email FROM Client c WHERE LOWER(c.email) = :email")
    String verifyEmailAlreadyExists(@Param("email") String email);

    @Query("SELECT c FROM Client c WHERE LOWER(c.email) = :email AND c.id = :id")
    Client verifyEmailWithIDAlreadyExists(@Param("id") Long id,
                                          @Param("email") String email);

    @Query("SELECT c FROM Client c WHERE LOWER(c.fullName) LIKE %:fullName%")
    List<Client> findByFullName(@Param("fullName") String fullName);

    @Query("SELECT c FROM Client c WHERE LOWER(c.fullName) LIKE %:param% OR LOWER(c.email) LIKE %:param% OR c.phone LIKE %:param%")
    List<Client> findByEveryAttribute(@Param("param") String param);



}
