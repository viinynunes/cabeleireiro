package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
