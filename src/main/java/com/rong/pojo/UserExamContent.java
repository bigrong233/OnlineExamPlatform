package com.rong.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UserExamContent {
    Exam exam;
    List<UserQuestionAndAnswer> userQuestionAndAnswerList;
}
