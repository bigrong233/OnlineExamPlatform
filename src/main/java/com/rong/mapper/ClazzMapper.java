package com.rong.mapper;

import com.rong.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClazzMapper {
    //查询用户创建的班级
    List<Clazz> selectByCreatorId(Integer userId);

    //用班级号查询班级
    Clazz selectByClazzId(Integer clazzId);

    //新增班级
    void insertClazz(Clazz clazz);

    //修改班级信息
    Integer updateClazz(Clazz clazz);

    //把班级创建者关联到班级创建表中
    void insertClazzCreator(ClazzCreator clazzCreator);

    //用户加入班级
    Integer insertClazzUser(ClazzUser clazzUser);

    //查询用户所加入的班级
    List<ClazzIJoined> selectClazzIJoinedByUserId(Integer userId);

    //把考试关联到班级
    Integer insertExamIntoClazz(ClazzExam clazzExam);

    //查询班级里的考试
    List<ExamInClazz> selectClazzExamByClazzId(Integer clazzId);

    //更新用户在班级的信息
    Integer updateClazzUser(ClazzUser clazzUser);

    //查询班级里的用户
    List<ClazzUser> selectClazzUserByClazzId(Integer clazzId);

    //删除班级里的某个用户
    Integer deleteClazzUser(ClazzUser clazzUser);

    //更新班级中考试的时间信息
    Integer updateClazzExam(ClazzExam clazzExam);

    //在班级中删除考试
    void deleteClazzExam(ClazzExam clazzExam);

    //查看班级考试成绩单
    List<ExamScoreInClazz> findExamScoreInClazz(ClazzExam clazzExam);
}
