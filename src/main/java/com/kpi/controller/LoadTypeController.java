package com.kpi.controller;

import com.kpi.model.LoadType;
import com.kpi.service.LoadTypeService;
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
@RequestMapping(value = "/loadtype")
public class LoadTypeController {
    @Autowired
    LoadTypeService loadtypeService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model){
        model.setViewName("loadtypeslist");
        model.addObject("loadtypes", loadtypeService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Integer id, ModelAndView model){
        try {
            loadtypeService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Integer id, ModelAndView model){
        LoadType loadtype = loadtypeService.getById(id);
        model.setViewName("loadtype");
        model.addObject("loadtype", loadtype);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(LoadType loadtype, ModelAndView model) {
        try {
            loadtypeService.update(loadtype);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("loadtype");
        LoadType loadtype= new LoadType();
        loadtype.setId(loadtypeService.getMaxId() + 1);
        model.addObject("loadtype", loadtype);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(LoadType loadtype, ModelAndView model) {
        try {
            loadtypeService.insert(loadtype);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
