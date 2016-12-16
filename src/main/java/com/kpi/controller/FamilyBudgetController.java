package com.kpi.controller;

import com.kpi.model.FamilyBudget;
import com.kpi.model.FinancialOperation;
import com.kpi.service.FamilyBudgetService;
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
@RequestMapping(value = "/familybudget")
public class FamilyBudgetController {
    @Autowired
    FamilyBudgetService familyBudgetService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model){
        model.setViewName("familybudgetslist");
        model.addObject("familybudgets", familyBudgetService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Integer id, ModelAndView model){
        try {
            familyBudgetService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Integer id, ModelAndView model){
        FamilyBudget familybudget = familyBudgetService.getById(id);
        System.out.println(familybudget.toString());
        model.setViewName("familybudget");
        model.addObject("familybudget", familybudget);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(FamilyBudget familybudget, ModelAndView model) {
        try {
            familyBudgetService.update(familybudget);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("familybudget");
        FamilyBudget familybudget = new FamilyBudget();
        familybudget.setId(familyBudgetService.getMaxId() + 1);
        model.addObject("familybudget", familybudget);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(FamilyBudget familybudget, ModelAndView model) {
        try {
            familyBudgetService.insert(familybudget);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
