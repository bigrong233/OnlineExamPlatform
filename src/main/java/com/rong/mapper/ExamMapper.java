package com.rong.mapper;

import com.rong.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamMapper {
    //增加考试
    void insertExam(Exam exam);

    //修改考试信息
    Integer updateExam(Exam exam);

    //把考试插入到到考试制作人表中
    Integer insertExamToExamCreator(ExamCreator examCreator);

    //用考试Id查找考试
    Exam selectByExamId(Integer examId);

    //用考试创建者Id查找考试
    List<ExamICreated> selectByCreatorId(Integer userId);

    //用考试ID查找考试内容
    ExamContent selectExamContentByExamId(Integer examId);

    //把试题添加到考试中
    Integer insertQuestionIntoExam(ExamQuestion question);

    //用用户id查找自己所在班级的考试
    List<ExamInClazz> selectJoinExamByUerId(Integer UserId);

    //利用公开考试名称进行模糊查询
    List<Exam> selectExamListByExamName(String str);

    //利用公开考试科目进行模糊查询
    List<Exam> selectExamListByExamSubject(String str);

    //保存用户参加考试的开始时间到成绩表
    void insertStartTimeToUserExam(UserExam userExam);

    //查询用户的某次考试的成绩表
    UserExam selectUserExamByUserIdAndExamId(UserExam userExam);

    //更新用户的考试成绩及完成考试时间到成绩表
    void updateUserExamByUserIdAndExamId(UserExam userExam);

    //查询用户参加过的考试
    List<ExamScore> selectExamScoreByUserId(Integer userId);

    //把用户答题的答案放入到userQuestion表中
    void insertUserQuestion(UserQuestion userQuestion);

    //查看用户考试详情
    UserExamContent selectUserExamContentByUserExam(UserExam userExam);
}