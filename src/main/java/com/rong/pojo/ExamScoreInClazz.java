package com.rong.pojo;

import lombok.Data;

@Data
public class ExamScoreInClazz {
    private String userClassId;
    private String userClassName;
    private Integer score;
}
