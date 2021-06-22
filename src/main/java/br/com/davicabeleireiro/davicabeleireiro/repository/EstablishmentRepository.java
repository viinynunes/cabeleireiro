package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
}
