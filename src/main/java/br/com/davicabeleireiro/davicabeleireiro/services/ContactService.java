package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ContactDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Contact;
import br.com.davicabeleireiro.davicabeleireiro.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public ContactDTO create(ContactDTO dto){
        return new ContactDTO(repository.save(new Contact(dto)));
    }

    public List<ContactDTO> findAll(){
        List<Contact> contactList = repository.findAll();
        List<ContactDTO> dtoList = new ArrayList<>();
        contactList.forEach(x -> dtoList.add(new ContactDTO(x)));

        return dtoList;
    }
}