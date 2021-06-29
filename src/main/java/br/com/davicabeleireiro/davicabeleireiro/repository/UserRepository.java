package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE LOWER(u.userName) = :username AND u.id = :id")
    User verifyUsernameWithIdAlreadyExists(@Param("username")String username, @Param("id")Long id);

    List<User> findByEnabledTrue();

    List<User> findByEnabledFalse();

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = :email")
    User verifyEmailAlreadyExists(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = :email AND u.id = :id")
    User verifyEmailWithIDAlreadyExists(@Param("email") String email, @Param("id") Long id);

    @Modifying
    @Query("UPDATE User u SET u.enabled = false WHERE u.id = :id")
    void disable(@Param("id") Long id);
}
