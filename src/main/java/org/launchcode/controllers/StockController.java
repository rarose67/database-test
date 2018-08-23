package org.launchcode.controllers;

import org.launchcode.models.Stock;
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
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockDao stockdao;

    @Autowired
    private UserDao userdao;

    @Autowired
    private DatabaseService databaseService;

    public void printSymbols()
    {
        System.out.println("\nStocks:\n");
        for (Stock stock : databaseService.findDividend(1.5, -1))
        {
            System.out.println("Symbol: " + stock.getSymbol() + ", " + stock.getName());
        }
    }

    // Request path: /stock
    @RequestMapping(value = "")
    public String index(Model model)  {

        //@CookieValue(value = "user", defaultValue = "none") String username
        /**if(username.equals("none")) {
            return "redirect:/user/login";
        } */

        printSymbols();

        model.addAttribute("stocks", stockdao.findAll());
        model.addAttribute("title", "My Stocks");

        return "stock/index";
    }

    @RequestMapping(value = "list")
    public String list(Model model)  {

        //@CookieValue(value = "user", defaultValue = "none") String username
        /**if(username.equals("none")) {
         return "redirect:/user/login";
         } */

        model.addAttribute("symbols", stockdao.findSymbols());
        model.addAttribute("title", "My symbols");

        return "stock/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddStockForm(Model model) {

        model.addAttribute("title", "Add Stock");
        model.addAttribute(new Stock());
        return "stock/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddStockForm(@ModelAttribute  @Valid Stock newStock,
                                       Errors errors, Model model, @RequestParam int month, @RequestParam int day,
                                      @RequestParam int year) {


        //User u = userdao.findByUsername(username).get(0);
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Stock");
            return "stock/add";
        }

        //newStock.setUser(u);

        GregorianCalendar date = new GregorianCalendar(year, month-1, day);
        newStock.setLastDividendDate(date);
        newStock.setWeekStartPrice(45.7);

        stockdao.save(newStock);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveStockForm(Model model, @CookieValue(value = "user", defaultValue = "none") String username) {

        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        User u = userdao.findByUsername(username).get(0);

        model.addAttribute("stocks", u.getStocks());
        model.addAttribute("title", "Remove Stock");
        return "stock/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveStockForm(@RequestParam int[] stockIds) {

        for (int stockId : stockIds) {
            Stock stock = stockdao.findOne(stockId);
            System.out.println("WP: $" + stock.getWeekStartPrice());
            stockdao.delete(stockId);
        }

        return "redirect:";
    }
}