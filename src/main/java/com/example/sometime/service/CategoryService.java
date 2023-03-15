package com.example.sometime.service;


import com.example.sometime.domain.Category;
import com.example.sometime.dto.CategoryDto;
import com.example.sometime.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long save(Category category) {
        Category category1 = categoryRepository.save(category);
        return category1.getId();
    }

    public Category find(Long id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
    }

    CategoryDto transform(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .uni(category.getUni()).build();
        return categoryDto;
    }
}
