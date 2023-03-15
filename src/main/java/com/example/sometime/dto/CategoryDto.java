package com.example.sometime.dto;


import com.example.sometime.domain.Board;
import com.example.sometime.domain.Category;
import com.example.sometime.domain.Uni;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;
    private Uni uni;
    private List<Board> boardList = new ArrayList<>();;

    public Category toEntity(){
        Category category = Category.builder()
                .name(name)
                .uni(uni).build();
        return category;
    }
    @Builder
    public CategoryDto(Long id, String name, Uni uni) {
        this.id = id;
        this.name = name;
        this.uni = uni;
    }
}
