package com.rong.pojo;

import lombok.Data;

@Data
public class ClazzUser {
    private Integer userId;
    private Integer clazzId;

    //下面两种属性方便班级创建者在输出班级考试成绩单时方便辨认自己的考生

    //用户在班级里的名称(真实姓名)
    private String userClazzName;
    //用户在班级里的id(学号)
    private String userClazzId;
}
