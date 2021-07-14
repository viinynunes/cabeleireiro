package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.CategoryDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import br.com.davicabeleireiro.davicabeleireiro.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO create(CategoryDTO dto){
        return new CategoryDTO(repository.save(new Category(dto)));
    }

    public CategoryDTO update(CategoryDTO dto) {
        var entity = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("ID "+ dto.getId() + " not found"));

        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setEnabled(true);
        entity.setServicesList(dto.getServicesList());

        return new CategoryDTO(repository.save(entity));
    }

    @Transactional
    public CategoryDTO disable(Long id){
        repository.disableCategory(id);

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " not found"));

        return new CategoryDTO(entity);
    }

    public List<CategoryDTO> findAll(){
        var categoryList = repository.findAll();
        List<CategoryDTO> dtoList = new ArrayList<>();
        categoryList.forEach(x -> dtoList.add(new CategoryDTO(x)));
        return dtoList;
    }

    public List<CategoryDTO> findByName(String name){
        var categoryList = repository.findByName(name);

        List<CategoryDTO> dtoList = new ArrayList<>();
        categoryList.forEach(x -> dtoList.add(new CategoryDTO(x)));
        return dtoList;
    }

    public List<CategoryDTO> findByEnabledTrue(){
        var categoryList = repository.findByEnabledTrue();
        List<CategoryDTO> dtoList = new ArrayList<>();
        categoryList.forEach(x -> dtoList.add(new CategoryDTO(x)));

        return dtoList;
    }

    public List<CategoryDTO> findByEnabledFalse(){
        var categoryList = repository.findByEnabledFalse();
        List<CategoryDTO> dtoList = new ArrayList<>();
        categoryList.forEach(x -> dtoList.add(new CategoryDTO(x)));

        return dtoList;
    }

}
