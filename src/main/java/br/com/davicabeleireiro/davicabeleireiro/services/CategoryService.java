package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.model.dto.CategoryDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import br.com.davicabeleireiro.davicabeleireiro.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO create(CategoryDTO dto){
        return new CategoryDTO(repository.save(new Category(dto)));
    }

    public List<CategoryDTO> findAll(){
        var categoryList = repository.findAll();
        List<CategoryDTO> dtoList = new ArrayList<>();
        categoryList.forEach(x -> dtoList.add(new CategoryDTO(x)));
        return dtoList;
    }

}
