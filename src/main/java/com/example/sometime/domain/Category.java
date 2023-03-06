package com.example.sometime.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(unique = true, length = 60) // UNIQUE, 이름 길이 제한
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uni_id")
    private Uni uni;
    private List<Board> boardList;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // 일대다. 주인은 Order.
    private List<Board> boardList = new ArrayList<>();;

    @Builder
    public Category(String name, Uni uni, List<Board> boardList) {
        this.name = name;
        this.uni = uni;
        this.boardList = boardList;
    }
}
