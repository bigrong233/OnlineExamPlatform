package com.rong.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UserExam {
    private Integer userId;
    private Integer examId;
    private Integer score;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
}
