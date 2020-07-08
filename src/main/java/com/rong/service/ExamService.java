package com.rong.service;

import com.rong.mapper.ExamMapper;
import com.rong.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    ExamMapper examMapper;

    @Autowired
    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    public List<ExamICreated> findExamByCreatorId(Integer userId) {
        return examMapper.selectByCreatorId(userId);
    }

    public Integer insertExam(Exam exam) {
        examMapper.insertExam(exam);
        return exam.getExamId();
    }

    public Exam findExamByExamId(Integer examId){
        return examMapper.selectByExamId(examId);
    }

    public Integer insertExamToExamCreator(ExamCreator examCreator) {
        return examMapper.insertExamToExamCreator(examCreator);
    }

    public ExamContent findExamContentByExamId(Integer examId) {
        return examMapper.selectExamContentByExamId(examId);
    }

    public Integer insertQuestionIntoExam(ExamQuestion examQuestion) {
        return examMapper.insertQuestionIntoExam(examQuestion);
    }

    public List<ExamInClazz> findJoinExamByUerId(Integer userId) {
        return examMapper.selectJoinExamByUerId(userId);
    }

    public List<Exam> findExamListByExamName(String str) {
        return examMapper.selectExamListByExamName(str);
    }
    public List<Exam> findExamListByExamSubject(String str) {
        return examMapper.selectExamListByExamSubject(str);
    }

    public void insertStartTimeToUserExam(UserExam userExam){
        examMapper.insertStartTimeToUserExam(userExam);
    }

    public UserExam findUserExamByUserIdAndExamId(UserExam userExam) {
        return examMapper.selectUserExamByUserIdAndExamId(userExam);
    }

    public void updateUserExamByUserIdAndExamId(UserExam userExam) {
        examMapper.updateUserExamByUserIdAndExamId(userExam);
    }

    public List<ExamScore> findExamScoreByUserId(Integer userId) {
        return examMapper.selectExamScoreByUserId(userId);
    }

    public void insertUserQuestion(UserQuestion userQuestion) {
        examMapper.insertUserQuestion(userQuestion);
    }

    public UserExamContent findUserExamContentByUserExam(UserExam userExam) {
        return examMapper.selectUserExamContentByUserExam(userExam);
    }

    public Boolean updateExam(Exam exam) {
        return examMapper.updateExam(exam) == 1;
    }
}
