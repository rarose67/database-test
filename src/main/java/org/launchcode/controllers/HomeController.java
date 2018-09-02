package org.launchcode.controllers;

import org.launchcode.models.Stock;
import org.launchcode.models.Test;
import org.launchcode.models.User;
import org.launchcode.models.data.DatabaseService;
import org.launchcode.models.data.StockDao;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.GregorianCalendar;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private Test test;

    @RequestMapping(value = "")
    public String index()  {


        test.printSymbols();

        return "redirect:/stock/list";
    }
}
