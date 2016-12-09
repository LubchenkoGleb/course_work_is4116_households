package com.kpi.controller;

import com.kpi.model.Subject;
import com.kpi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by gleb on 09.12.16.
 */

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model){
        model.setViewName("subjectslist");
        model.addObject("subjects", subjectService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Integer id, ModelAndView model){
        try {
            subjectService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Integer id, ModelAndView model){
        Subject subject = subjectService.getById(id);
        model.setViewName("subject");
        model.addObject("subject", subject);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(Subject subject, ModelAndView model) {
        try {
            subjectService.update(subject);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("subject");
        Subject subject= new Subject();
        subject.setId(subjectService.getMaxId() + 1);
        model.addObject("subject", subject);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(Subject subject, ModelAndView model) {
        try {
            subjectService.insert(subject);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
