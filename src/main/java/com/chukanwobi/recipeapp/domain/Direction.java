package com.chukanwobi.recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String steps;

    @ManyToOne
    private Recipe recipe;

    public Direction(String steps) {
        this.steps = steps;
    }
}
