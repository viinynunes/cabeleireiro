package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("SELECT p FROM Permission p WHERE p.description = :description")
    Permission findByDescription(@Param("description") String description);

    List<Permission> findByEnabledTrue();

    List<Permission> findByEnabledFalse();

    @Modifying
    @Query("UPDATE Permission p SET p.enabled = false WHERE p.id =:id")
    void disable(@Param("id") Long id);
}
