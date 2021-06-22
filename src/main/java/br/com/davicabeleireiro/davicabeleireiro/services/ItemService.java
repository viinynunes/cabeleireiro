package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.ItemDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import br.com.davicabeleireiro.davicabeleireiro.repository.CategoryRepository;
import br.com.davicabeleireiro.davicabeleireiro.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ItemDTO create(ItemDTO dto){
        dto.setCategory(categoryRepository.findById(dto.getCategory().getId()).get());

        return new ItemDTO(repository.save(new Item(dto)));
    }

    public List<ItemDTO> findAll(){
        var entityList = repository.findAll();
        List<ItemDTO> dtoList = new ArrayList<>();
        entityList.forEach(x -> dtoList.add(new ItemDTO(x)));
        return dtoList;
    }
}
