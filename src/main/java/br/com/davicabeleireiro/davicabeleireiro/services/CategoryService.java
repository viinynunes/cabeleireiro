package br.com.davicabeleireiro.davicabeleireiro.services;

import br.com.davicabeleireiro.davicabeleireiro.exception.ResourceNotFoundException;
import br.com.davicabeleireiro.davicabeleireiro.model.dto.CategoryDTO;
import br.com.davicabeleireiro.davicabeleireiro.model.entities.Category;
import br.com.davicabeleireiro.davicabeleireiro.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<CategoryDTO> findAll(Pageable pageable){
        var categoryList = repository.findAll(pageable);
        return categoryList.map(this::convertToVO);
    }

    private CategoryDTO convertToVO(Category category){
        return new CategoryDTO(category);
    }

    public Page<CategoryDTO> findByName(String name, Pageable pageable){
        var categoryList = repository.findByName(name, pageable);

        return categoryList.map(this::convertToVO);
    }

    public Page<CategoryDTO> findByEnabledTrue(Pageable pageable){
        var categoryList = repository.findByEnabledTrue(pageable);
        return categoryList.map(this::convertToVO);
    }

    public Page<CategoryDTO> findByEnabledFalse(Pageable pageable){
        var categoryList = repository.findByEnabledFalse(pageable);
        return categoryList.map(this::convertToVO);
    }

}
