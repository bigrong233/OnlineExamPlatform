package com.rong.controller;

import com.rong.pojo.Question;
import com.rong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionController {

    QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/goUpdate")
    @ResponseBody
    public Integer updateQuestion(Question question){
        return questionService.updateQuestion(question);
    }

    @PostMapping("/goFindQuestion")
    public String goFindQuestion(Model model,String str){
        model.addAttribute("str", str);
        model.addAttribute("questionList",
                questionService.findQuestionListByQuestionContent(str));
        return "content/findQuestion";
    }
    @GetMapping("/goAnswerQuestion")
    public String goAnswerQuestion(Model model,Integer questionId) {
        model.addAttribute("questionMsg",
                questionService.findQuestionByQuestionId(questionId));
        return "content/question";
    }
}
