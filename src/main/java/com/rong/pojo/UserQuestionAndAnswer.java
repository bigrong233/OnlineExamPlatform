package com.rong.pojo;

import lombok.Data;

@Data
public class UserQuestionAndAnswer {
    private Integer questionId;
    private String questionContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String analyze;
    private Integer score;
    private String userAnswer;
}
