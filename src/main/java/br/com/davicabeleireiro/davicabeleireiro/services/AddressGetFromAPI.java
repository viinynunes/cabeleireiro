package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.model.entities.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressGetFromAPI {

    public Address getAddressFromCEP(String cep){

        if (cep != null){
            RestTemplate rest = new RestTemplate();
            return rest.getForObject("https://viacep.com.br/ws/"+ cep +"/json/", Address.class);
        }
        return null;
    }
}