package com.rong.mapper;

import com.rong.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    void insertQuestion(Question question);

    Integer updateQuestion(Question question);

    List<Question> selectByQuestionContent(String str);

    Question selectQuestionByQuestionId(Integer questionId);
}
