package com.rong.pojo;

import lombok.Data;

@Data
public class Exam {
    private Integer examId;
    private String examName;
    private String examSubject;
    private Integer isPublic;
}
