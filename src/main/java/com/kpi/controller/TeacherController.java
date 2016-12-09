package com.kpi.controller;

import com.kpi.model.Teacher;
import com.kpi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by gleb on 06.12.16.
 */
@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model){
        model.setViewName("teacherslist");
        model.addObject("teachers", teacherService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Integer id, ModelAndView model){
        try {
            teacherService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Integer id, ModelAndView model){
        Teacher teacher = teacherService.getById(id);
        model.setViewName("teacher");
        model.addObject("teacher", teacher);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(Teacher teacher, ModelAndView model) {
        try {
            teacherService.update(teacher);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("teacher");
        Teacher teacher = new Teacher();
        teacher.setId(teacherService.getMaxId() + 1);
        model.addObject("teacher", teacher);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(Teacher teacher, ModelAndView model) {
        try {
            teacherService.insert(teacher);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
