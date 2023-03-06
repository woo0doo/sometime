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
public class Uni {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uni_id")
    private Long id;

    @Column(name = "uni_name")
    private String name;

    @OneToMany(mappedBy = "user_id")
    private List<User> userList;

    @OneToMany(mappedBy = "category_id")
    private List<Category> categoryList;

    @Builder
    public Uni(String name, List<User> userList, List<Category> categoryList) {
        this.name = name;
        this.userList = userList;
        this.categoryList = categoryList;
    }
}
