package com.rong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rong.pojo.*;
import com.rong.service.ClazzService;
import com.rong.service.ExamService;
import com.rong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class ExamController {

    ExamService examService;

    QuestionService questionService;

    ClazzService clazzService;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setClazzService(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    @GetMapping("/findExamICreated")
    public String findExamICreated(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userMsg");
        model.addAttribute("examICreatedList", examService.findExamByCreatorId(user.getUserId()));
        model.addAttribute("clazzList", clazzService.findByCreatorId(user.getUserId()));
        return "content/showMyExam";
    }

    @PostMapping("/goInsertExam")
    @ResponseBody
    public Integer goInsertExam(Exam exam) {
        return examService.insertExam(exam);
    }

    @PostMapping("/updateExam")
    @ResponseBody
    public Boolean updateExam(Exam exam) {
        return examService.updateExam(exam);
    }

    @PostMapping("/goInsertExamToExamCreator")
    @ResponseBody
    public Integer goInsertExamToExamCreator(ExamCreator examCreator) {
        examCreator.setCreateTime(LocalDateTime.now());
        return examService.insertExamToExamCreator(examCreator);
    }

    @GetMapping("/goWriteExam/{examId}")
    public String goWriteExam(Model model, @PathVariable Integer examId) {
        ExamContent examContent = examService.findExamContentByExamId(examId);
        if (examContent == null) {
            examContent = new ExamContent();
            examContent.setExam(examService.findExamByExamId(examId));
            model.addAttribute("examMsg", examContent);
        }
        model.addAttribute("examMsg", examContent);
        return "content/writeExam";
    }

    @PostMapping("/insertQuestionIntoExam")
    @ResponseBody
    public Integer insertQuestionIntoExam(Question question, Integer examId) {
        Integer questionId = questionService.insertQuestion(question);
        if (question == null) {
            return null;
        } else {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExamId(examId);
            examQuestion.setQuestionId(questionId);
            return examService.insertQuestionIntoExam(examQuestion);
        }
    }

    @GetMapping("/findJoinExam")
    public String findJoinExam(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userMsg");
        model.addAttribute("joinExamList", examService.findJoinExamByUerId(user.getUserId()));
        return "content/joinExam";
    }

    @GetMapping("/goAnswerExam")
    public String goAnswerExam(HttpSession session, HttpServletResponse response, Model model, ClazzExam clazzExam) throws IOException {
        model.addAttribute("clazzExamMsg", clazzExam);
        LocalDateTime localDateTime = LocalDateTime.now();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (clazzExam.getStartTime() != null && clazzExam.getEndTime() != null) {
            if (localDateTime.isBefore(clazzExam.getStartTime())) {
                out.print("<script>alert('考试未开始');</script>");
                return findJoinExam(session, model);
            }
            if (localDateTime.isAfter(clazzExam.getEndTime())) {
                out.print("<script>alert('考试已结束');</script>");
                return findJoinExam(session, model);
            }
        }
        ExamContent examContent = examService.findExamContentByExamId(clazzExam.getExamId());
        if (examContent == null) {
            out.print("<script>alert('该考试还未准备好哦~');</script>");
            return findJoinExam(session, model);
        }
        User user = (User) session.getAttribute("userMsg");
        UserExam userExam = new UserExam();
        userExam.setUserId(user.getUserId());
        userExam.setExamId(clazzExam.getExamId());
        UserExam flag = examService.findUserExamByUserIdAndExamId(userExam);
        if (flag != null && flag.getEndTime() != null) {
            out.print("<script>alert('你已经参加过该考试了,请到“已完成的考试”模块查看');</script>");
            return findJoinExam(session, model);
        } else if (flag == null) {
            userExam.setStartTime(localDateTime);
            examService.insertStartTimeToUserExam(userExam);
        }
        model.addAttribute("examMsg", examContent);
        return "content/answerExam";
    }

    @GetMapping("/goAnswerPublicExam")
    public String goAnswerPublicExam(HttpServletResponse response,Model model,Integer examId) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        ExamContent examContent = examService.findExamContentByExamId(examId);
        if (examContent == null) {
            out.print("<script>alert('该考试还未准备好哦~');</script>");
            return "content/findExam";
        }
        model.addAttribute("examMsg", examContent);
        return "content/answerPublicExam";
    }

    @PostMapping("/goFindExam")
    public String goFindExam(Model model, String str,Integer type) {
        model.addAttribute("str", str);
        if (type == 1) {
            model.addAttribute("examList",
                    examService.findExamListByExamName(str));
        }else{
            model.addAttribute("examList",
                    examService.findExamListByExamSubject(str));
        }
        return "content/findExam";
    }

    @PostMapping("/getUserAnswer")
    @ResponseBody
    public Integer getUserAnswer(@RequestParam("userQuestion") String userQuestionStr,
                                 @RequestParam("score") String score,
                                 ClazzExam clazzExam) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        UserQuestion userQuestion = mapper.readValue(userQuestionStr, UserQuestion.class);
        LocalDateTime endTime = LocalDateTime.now();
        UserExam userExam = new UserExam();
        userExam.setUserId(userQuestion.getUserId());
        userExam.setExamId(clazzExam.getExamId());
        userExam = examService.findUserExamByUserIdAndExamId(userExam);
        userExam.setEndTime(endTime);
        if (endTime.isAfter(clazzExam.getEndTime())
                || ChronoUnit.MINUTES.between(userExam.getEndTime(), userExam.getStartTime()) > clazzExam.getExamTime()) {
            userExam.setScore(0);
        } else {
            userExam.setScore(new Integer(score));
        }
        examService.updateUserExamByUserIdAndExamId(userExam);
        examService.insertUserQuestion(userQuestion);
        return userExam.getScore();
    }

    @GetMapping("/goShowExamIJoined")
    public String goShowExamIJoined(Model model, HttpSession session) {
        model.addAttribute("examScoreList",
                examService.findExamScoreByUserId(((User) session.getAttribute("userMsg")).getUserId()));
        return "content/showExamIJoined";
    }

    @GetMapping("/goShowMyExamAnswer")
    public String goShowMyExamAnswer(Model model,HttpSession session,Integer examId) {
        UserExam userExam = new UserExam();
        userExam.setUserId(((User) session.getAttribute("userMsg")).getUserId());
        userExam.setExamId(examId);
        System.out.println(userExam);
        model.addAttribute("userExamContent", examService.findUserExamContentByUserExam(userExam));
        return "content/showMyExamAnswer";
    }

}
