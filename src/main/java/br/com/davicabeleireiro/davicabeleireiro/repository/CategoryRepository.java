package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE %:name%")
    List<Category> findByName(@Param("name") String name);

    List<Category> findByEnabledTrue();

    List<Category> findByEnabledFalse();

    @Modifying
    @Query("UPDATE Category c SET c.enabled = false WHERE c.id =:id")
    void disableCategory(@Param("id")Long id);
}
