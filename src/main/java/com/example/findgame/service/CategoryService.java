package com.example.findgame.service;

import com.example.findgame.dto.CategoryDTO;
import com.example.findgame.entity.Category;
import com.example.findgame.mapper.CategoryMapper;
import com.example.findgame.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        System.out.println("CategoryDTO: " + categoryDTO.getName()); // Debug log
        Category category = CategoryMapper.INSTANCE.categoryDTOToCategory(categoryDTO);
        System.out.println("Mapped Category: " + category.getName()); // Debug log
        category = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }


    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }

    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryDTO.getId()));
        // Map the changes onto the existingCategory. Assume all fields in DTO should overwrite existing fields
        existingCategory.setName(categoryDTO.getName());
        // Save the updated category
        existingCategory = categoryRepository.save(existingCategory);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(existingCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
