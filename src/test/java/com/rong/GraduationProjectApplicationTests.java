package com.rong;

import com.rong.pojo.ClazzExam;
import com.rong.pojo.ExamScoreInClazz;
import com.rong.service.ClazzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class GraduationProjectApplicationTests {

    @Autowired
    ClazzService clazzService;

    @Test
    void contextLoads() {
        ClazzExam clazzExam = new ClazzExam();
        clazzExam.setClazzId(10004);
        clazzExam.setExamId(10006);
        List<ExamScoreInClazz> examScoreInClazzList = clazzService.findExamScoreInClazz(clazzExam);
        for (ExamScoreInClazz e:examScoreInClazzList
             ) {
            System.out.println(e);
        }
    }
}
