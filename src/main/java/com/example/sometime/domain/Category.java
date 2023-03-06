package com.example.sometime.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Uni uni;

    @OneToMany(mappedBy = "board_id")
    private List<Board> boardList;

    @Builder
    public Category(String name, Uni uni, List<Board> boardList) {
        this.name = name;
        this.uni = uni;
        this.boardList = boardList;
    }
}
