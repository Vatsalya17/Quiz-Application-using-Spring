package com.Vatsalya.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public String getTitle() {
        return title;
    }

    public List<questions> getQuestions() {
        return questions;
    }

    private String title;
    @ManyToMany
    private List<questions> questions;

    public void setQuestions(List<questions> questions) {
        this.questions = questions;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
