package com.rong.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UserQuestion {
    private Integer userId;
    private List<QuestionAnswer> questionAnswerList;
}
