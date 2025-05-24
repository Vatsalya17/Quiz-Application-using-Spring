package com.Vatsalya.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;

    public Integer getId() {
        return id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption4() {
        return option4;
    }

    public String getOption3() {
        return option3;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
