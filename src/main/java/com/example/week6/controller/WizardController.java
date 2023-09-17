package com.example.week6.controller;

import com.example.week6.pojo.Wizard;
import com.example.week6.pojo.Wizards;
import com.example.week6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WizardController {

    @Autowired
    private WizardService wizardService;

    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public Wizards allwizards(){
        return wizardService.getAllWizards();
    }

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public Wizard addWizard(@RequestBody MultiValueMap<String, String> data){
        Map<String, String> d = data.toSingleValueMap();
        Wizard newData = new Wizard(null, d.get("sex"), d.get("name"), d.get("school"), d.get("house"), Double.parseDouble(d.get("money")), d.get("position"));
        wizardService.addWizard(newData); //เอาเข้า db
        return newData;
    }

    @RequestMapping(value = "/updateWizard", method = RequestMethod.POST)
    public Wizard updateWizard(@RequestBody MultiValueMap<String, String> data){
        Map<String, String> d = data.toSingleValueMap();
        Wizard newData = new Wizard(d.get("_id"), d.get("sex"), d.get("fullName"), d.get("school"), d.get("house"), Double.parseDouble(d.get("money")), d.get("position"));
        wizardService.updateWizard(newData); //เอาเข้า db
        return newData;
    }

    @RequestMapping(value = "/deleteWizard", method = RequestMethod.POST)
    public String deleteWizard(@RequestBody MultiValueMap<String, String> data){
        Map<String, String> d = data.toSingleValueMap();
        wizardService.deleteWizard(d.get("_id")); //เอาเข้า db
        return "Delete Success";
    }

}
