package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
