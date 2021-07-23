package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE %:name%")
    Page<Category> findByName(@Param("name") String name, Pageable pageable);

    Page<Category> findByEnabledTrue(Pageable pageable);

    Page<Category> findByEnabledFalse(Pageable pageable);

    @Modifying
    @Query("UPDATE Category c SET c.enabled = false WHERE c.id =:id")
    void disableCategory(@Param("id")Long id);
}
