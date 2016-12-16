package com.kpi.controller;

import com.kpi.model.FamilyBudget;
import com.kpi.model.FamilyMember;
import com.kpi.service.FamilyMemberService;
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
@RequestMapping(value = "/familymember")
public class FamilyMemberController {

    @Autowired
    FamilyMemberService familyMemberService;

    @RequestMapping(value = "/get")
    public ModelAndView get(ModelAndView model){
        model.setViewName("familymemberslist");
        model.addObject("familymembers", familyMemberService.getAll());
        return model;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam Integer id, ModelAndView model){
        try {
            familyMemberService.delete(id);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preUpdate")
    public ModelAndView preUpdate(@RequestParam Integer id, ModelAndView model){
        FamilyMember familyMember = familyMemberService.getById(id);
        model.setViewName("familymember");
        model.addObject("familymember", familyMember);
        model.addObject("operation", "update");
        return model;
    }

    @RequestMapping(value = "/update")
    public ModelAndView update(FamilyMember familyMember, ModelAndView model) {
        try {
            familyMemberService.update(familyMember);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }

    @RequestMapping(value = "/preInsert")
    public ModelAndView preInsert(ModelAndView model) throws SQLException {
        model.setViewName("familymember");
        FamilyMember familyMember= new FamilyMember();
        familyMember.setId(familyMemberService.getMaxId() + 1);
        model.addObject("familymember", familyMember);
        model.addObject("operation", "insert");
        return model;
    }

    @RequestMapping(value = "/insert")
    public ModelAndView insert(FamilyMember familyMember, ModelAndView model) {
        try {
            familyMemberService.insert(familyMember);
        } catch (SQLException e) {
            model.addObject("exception", e.getMessage());
            e.printStackTrace();
        }
        return get(model);
    }
}
