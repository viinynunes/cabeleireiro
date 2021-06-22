package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
