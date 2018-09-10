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
        System.out.println("\nControler Stocks:\n");
        for (Stock stock : databaseService.findDividend(1.5, -1))
        {
            System.out.println("Symbol: " + stock.getSymbol() + ", " + stock.getName());
        }
    }

    @RequestMapping(value = "")
    public String index(Model model)  {

        //@CookieValue(value = "user", defaultValue = "none") String username
        /**if(username.equals("none")) {
         return "redirect:/user/login";
         } */

        printSymbols();

        ArrayList<Integer> pageNumbers = new ArrayList<>();
        for (int i=0; i< stockdao.findAll().size(); i++)
        {
            pageNumbers.add(i+1);
        }

        model.addAttribute("stocks", stockdao.findAll());
        model.addAttribute("pageNums", pageNumbers);
        model.addAttribute("title", "My Stocks");
        model.addAttribute("page", 1);
        model.addAttribute("perPage", 2);

        return "stock/index3";
    }

    // Request path: /stock
    @RequestMapping(value = "{page}")
    public String page(Model model, @PathVariable int page)  {

        //@CookieValue(value = "user", defaultValue = "none") String username
        /**if(username.equals("none")) {
            return "redirect:/user/login";
        } */

        printSymbols();

        if (page < 1)
        {
            page = 1;
        }

        int perpage = 3;
        int max = stockdao.findAll().size();
        int rangestart = ((page - 1) * perpage);
        int rangeend = (page * perpage);

        if (rangeend > max)
        {
            rangeend = max;
        }

        ArrayList<Stock> stockSubList = new ArrayList<>(
                stockdao.findAll().subList(rangestart,rangeend));

        //model.addAttribute("stocks", stockdao.findAll());
        model.addAttribute("stocks", stockSubList);
        model.addAttribute("title", "My Stocks");

        return "stock/index2";
    }

    @RequestMapping(value = "list")
    public String list(Model model)  {

        //@CookieValue(value = "user", defaultValue = "none") String username
        /**if(username.equals("none")) {
         return "redirect:/user/login";
         } */

        model.addAttribute("symbols", stockdao.findSymbols());
        model.addAttribute("title", "My symbols");
        model.addAttribute("color", "red");

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
