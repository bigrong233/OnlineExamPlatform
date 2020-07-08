package com.rong.service;

import com.rong.mapper.ClazzMapper;
import com.rong.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService {


    ClazzMapper clazzMapper;

    @Autowired
    public void setClazzMapper(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }


    public List<Clazz> findByCreatorId(Integer userId) {
        return clazzMapper.selectByCreatorId(userId);
    }

    public Clazz findByClazzId(Integer clazzId) {
        return clazzMapper.selectByClazzId(clazzId);
    }

    public Integer insertClazz(Clazz clazz) {
        clazzMapper.insertClazz(clazz);
        return clazz.getClazzId();
    }

    public boolean updateClazz(Clazz clazz) {
        return clazzMapper.updateClazz(clazz) == 1;
    }

    public void insertClazzToCreator(ClazzCreator clazzCreator) {
        clazzMapper.insertClazzCreator(clazzCreator);
    }

    public Integer insertUserToClazz(ClazzUser clazzUser) {
        return clazzMapper.insertClazzUser(clazzUser);
    }

    public List<ClazzIJoined> findClazzIJoinedByUserId(Integer userId) {
        return clazzMapper.selectClazzIJoinedByUserId(userId);
    }

    public Integer insertExamIntoClazz(ClazzExam clazzExam) {
        return clazzMapper.insertExamIntoClazz(clazzExam);
    }

    public List<ExamInClazz> findClazzExamByClazzId(Integer clazzId) {
        return clazzMapper.selectClazzExamByClazzId(clazzId);
    }

    public boolean updateClazzUser(ClazzUser clazzUser) {
        return clazzMapper.updateClazzUser(clazzUser) == 1;
    }

    public List<ClazzUser> findClazzUserByClazzId(Integer clazzId) {
        return clazzMapper.selectClazzUserByClazzId(clazzId);
    }

    public Boolean deleteClazzUser(ClazzUser clazzUser) {
        return clazzMapper.deleteClazzUser(clazzUser) == 1;
    }

    public Boolean updateClazzExam(ClazzExam clazzExam) {
        return clazzMapper.updateClazzExam(clazzExam) == 1;
    }

    public void deleteClazzExam(ClazzExam clazzExam){
        clazzMapper.deleteClazzExam(clazzExam);
    }

    public List<ExamScoreInClazz> findExamScoreInClazz(ClazzExam clazzExam) {
        return clazzMapper.findExamScoreInClazz(clazzExam);
    }
}

