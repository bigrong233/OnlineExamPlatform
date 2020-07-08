package com.rong.controller;

import com.rong.pojo.*;
import com.rong.service.ClazzService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClazzController {

    ClazzService clazzService;

    @Autowired
    public void setClazzService(ClazzService clazzService) {
        this.clazzService = clazzService;
    }

    @PostMapping("/goCreateClazz")
    @ResponseBody
    public Integer goCreateClazz(Clazz clazz) {
        return clazzService.insertClazz(clazz);
    }

    @PostMapping("/goInsertClazzToCreator")
    @ResponseBody
    public void insertClazzToCreator(ClazzCreator clazzCreator) {
        clazzService.insertClazzToCreator(clazzCreator);
    }

    @GetMapping("/goShowMyClazz")
    public String goShowMyClass(HttpSession session, Model model) {
        User user = (User) session.getAttribute("userMsg");
        model.addAttribute("clazzList", clazzService.findByCreatorId(user.getUserId()));
        return "content/showMyClazz";
    }

    @PostMapping("/goUpdateClazzMsg")
    @ResponseBody
    public boolean goUpdateClazzMsg(Clazz clazz) {
        return clazzService.updateClazz(clazz);
    }

    @PostMapping("/goSearchClazz")
    public String goSearchClazz(Integer searchId, Integer type, Model model) {
        List<Clazz> clazzList = new ArrayList<>();
        model.addAttribute("searchId", searchId);
        if (type == 1) {
            Clazz clazz = clazzService.findByClazzId(searchId);
            if (clazz != null) {
                clazzList.add(clazz);
                model.addAttribute("clazzList", clazzList);
                return "content/joinClazz";
            }
        }
        model.addAttribute("clazzList", clazzService.findByCreatorId(searchId));
        return "content/joinClazz";
    }

    @PostMapping("/goJoinClazz")
    @ResponseBody
    public String goJoinClazz(ClazzUser clazzUser) {
        return "" + clazzService.insertUserToClazz(clazzUser);
    }

    @GetMapping("/goFindClazzIJoined")
    public String goFindClazzIJoined(HttpSession session, Model model) {
        model.addAttribute("clazzIJoinedList",
                clazzService.findClazzIJoinedByUserId(((User) session.getAttribute("userMsg")).getUserId()));
        return "content/showClazzIJoined";
    }

    @PostMapping("/insertExamIntoClazz")
    @ResponseBody
    public Integer insertExamIntoClazz(ClazzExam clazzExam) {
        return clazzService.insertExamIntoClazz(clazzExam);
    }

    @GetMapping("/goShowClazzExam")
    public String goShowClazzExam(Model model, Integer clazzId) {
        model.addAttribute("examList",
                clazzService.findClazzExamByClazzId(clazzId));
        return "content/showClazzExam";
    }

    @PostMapping("/goUpdateClazzUser")
    @ResponseBody
    public Boolean goUpdateClazzUser(ClazzUser clazzUser, HttpSession session) {
        if (clazzUser.getUserId() == null) {
            clazzUser.setUserId(((User) session.getAttribute("userMsg")).getUserId());
        }
        return clazzService.updateClazzUser(clazzUser);
    }

    @GetMapping("/goManageClazzUser")
    public String manageClazzUser(Model model, Integer clazzId) {
        model.addAttribute("userList",
                clazzService.findClazzUserByClazzId(clazzId));
        return "content/manageClazzUser";
    }

    @GetMapping("/goDeleteClazzUser")
    public String goDeleteClazzUser(Model model, ClazzUser clazzUser) {
        clazzService.deleteClazzUser(clazzUser);
        return manageClazzUser(model, clazzUser.getClazzId());
    }

    @GetMapping("/goManageClazzExam")
    public String goManageClazzExam(Model model, Integer clazzId) {
        model.addAttribute("examList",
                clazzService.findClazzExamByClazzId(clazzId));
        return "content/manageClazzExam";
    }

    @PostMapping("/goUpdateClazzExam")
    @ResponseBody
    public Boolean goUpdateClazzExam(ClazzExam clazzExam) {
        return clazzService.updateClazzExam(clazzExam);
    }

    @GetMapping("/goDeleteClazzExam")
    public String goDeleteClazzExam(Model model, ClazzExam clazzExam) {
        clazzService.deleteClazzExam(clazzExam);
        return goManageClazzExam(model, clazzExam.getClazzId());
    }

    @GetMapping("/goShowExamScoreList")
    public String goShowExamScoreList(Model model, ClazzExam clazzExam, String examName) {
        model.addAttribute("clazzExam", clazzExam);
        model.addAttribute("examName", examName);
        model.addAttribute("exList",
                clazzService.findExamScoreInClazz(clazzExam));
        return "content/showExamScoreList";
    }

    @GetMapping("/goPrint")
    public void goPrint(HttpServletResponse response,ClazzExam clazzExam, String examName) throws IOException {
        List<ExamScoreInClazz> list = clazzService.findExamScoreInClazz(clazzExam);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(examName);
        String[] headers = {"学号/工号", "姓名", "分数"};
        int rowNum = 0;
        HSSFRow row = sheet.createRow(rowNum);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (ExamScoreInClazz score : list) {
            rowNum++;
            HSSFRow mainRow = sheet.createRow(rowNum);
            mainRow.createCell(0).setCellValue(score.getUserClassId());
            mainRow.createCell(1).setCellValue(score.getUserClassName());
            mainRow.createCell(2).setCellValue(score.getScore());
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment;filename=scoreList.xls");
        response.setContentType("application/msexcel");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
