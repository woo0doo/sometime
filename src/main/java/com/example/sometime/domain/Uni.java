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
public class Uni {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uni_id")
    private Long id;

    @Column(name = "uni_name", unique = true, length = 60) // UNIQUE, 이름 길이 제한
    private String name;

    @OneToMany(mappedBy = "uni", cascade = CascadeType.ALL)
    private List<User> userList;

    @OneToMany(mappedBy = "uni", cascade = CascadeType.ALL) // 일대다
    private List<Category> categoryList;

    @Builder
    public Uni(String name, List<User> userList, List<Category> categoryList) {
        this.name = name;
        this.userList = userList;
        this.categoryList = categoryList;
    }
}
