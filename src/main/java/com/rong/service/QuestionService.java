package com.rong.service;

import com.rong.mapper.QuestionMapper;
import com.rong.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    QuestionMapper questionMapper;

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public List<Question> findQuestionListByQuestionContent(String str){
        return questionMapper.selectByQuestionContent(str);
    };

    public Integer insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
        return question.getQuestionId();
    }

    public Integer updateQuestion(Question question) {
        return questionMapper.updateQuestion(question);
    }

    public Question findQuestionByQuestionId(Integer questionId) {
        return questionMapper.selectQuestionByQuestionId(questionId);
    }

}
