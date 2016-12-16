package com.kpi.controller;

import com.kpi.model.FamilyMember;
import com.kpi.model.FinancialOperation;
import com.kpi.service.FinancialOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * Created by gleb on 06.12.16.
 */
@Controller
@RequestMapping(value = "/financialoperation")
public class FinancialOperationController {

    @Autowired
    FinancialOperationService financialOperationService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model){
        model.setViewName("financialoperationslist");
        model.addObject("financialoperations", financialOperationService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Integer id, ModelAndView model){
        try {
            financialOperationService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Integer id, ModelAndView model){
        FinancialOperation financialOperation = financialOperationService.getById(id);
        model.setViewName("financialoperation");
        model.addObject("financialoperation", financialOperation);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(FinancialOperation financialOperation, ModelAndView model) {
        try {
            financialOperationService.update(financialOperation);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("financialoperation");
        FinancialOperation financialOperation= new FinancialOperation();
        financialOperation.setId(financialOperationService.getMaxId() + 1);
        model.addObject("financialoperation", financialOperation);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(FinancialOperation financialOperation, ModelAndView model) {
        try {
            financialOperationService.insert(financialOperation);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
