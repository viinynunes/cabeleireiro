package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.ItemDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Item;
import br.com.davicabeleireiro.davicabeleireiro.repository.CategoryRepository;
import br.com.davicabeleireiro.davicabeleireiro.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ItemDTO create(ItemDTO dto){
        dto.setCategory(categoryRepository.findById(dto.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException("Category "+ dto.getCategory().getId()+" not found")));

        return new ItemDTO(repository.save(new Item(dto)));
    }

    public Page<ItemDTO> findAll(Pageable pageable){
        var entityList = repository.findAll(pageable);
        return entityList.map(this::convertToDTO);
    }

    private ItemDTO convertToDTO(Item item){
        return new ItemDTO(item);
    }
}
