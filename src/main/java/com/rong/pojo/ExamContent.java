package com.rong.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ExamContent {
    private Exam exam;
    private List<Question> questionList;
}
