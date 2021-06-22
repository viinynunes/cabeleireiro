package br.com.davicabeleireiro.davicabeleireiro.repository;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
