package org.launchcode.controllers;

import org.launchcode.models.Stock;
import org.launchcode.models.StockCompareType;
import org.launchcode.models.data.StockDao;
import org.launchcode.models.data.UserDao;
import org.launchcode.models.forms.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private StockDao stockdao;

    @Autowired
    private UserDao userdao;

    public  ArrayList<Stock> subQuery(ArrayList<Stock> queryResults, ArrayList<Stock> finalResults)
    {
        if (finalResults.isEmpty())
        {
            return queryResults;
        }

        ArrayList<Integer> matchIds = new ArrayList<>();
        ArrayList<Integer> finalIds = new ArrayList<>();
        ArrayList<Stock> newResults;
        int id;

        for (int i=0; i<finalResults.size(); i++)
        {
            id = finalResults.get(i).getId();
            finalIds.add(id);
        }

        for (int i=0; i<queryResults.size(); i++)
        {
            id = queryResults.get(i).getId();
            if (finalIds.contains(id))
            {
                matchIds.add(id);
            }
        }

        newResults = (ArrayList<Stock>) stockdao.findAll(matchIds);
        return newResults;
    }

    // Request path: /stock
    @RequestMapping(value = "")
    public String index(Model model)  {

        model.addAttribute("title", "Search");
        StockCompareType[] types = StockCompareType.values();
        model.addAttribute("types", types);
        model.addAttribute(new SearchForm());
        return "search";
    }

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String processSearchForm(@ModelAttribute  @Valid SearchForm newSearch, Errors errors, Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Search");
            StockCompareType[] types = StockCompareType.values();
            model.addAttribute("types", types);
            return "search";
        }

        ArrayList<Stock> foundStocks = new ArrayList<>();
        ArrayList<Stock> matchingStocks;

        GregorianCalendar date = new GregorianCalendar(newSearch.getYear(),
                newSearch.getMonth()-1, newSearch.getDay());
        newSearch.setDivDate(date);

        //System.out.println("\nName:" + newSearch.getName());
        //System.out.println("\nDividend:" + newSearch.getDividend());
        //System.out.println("\nDivComp:" + newSearch.getDivComp().name());

        if (!(newSearch.getName().equals("")))
        {
            matchingStocks = (ArrayList<Stock>) stockdao.findByName(newSearch.getName());
            foundStocks = subQuery(matchingStocks, foundStocks);
        }

        //if ((newSearch.getDividend() > 0.0) && (newSearch.getDivComp().getName().equals(StockCompareType.LESS.getName())))
        if ((newSearch.getDividend() > 0.0) && (newSearch.getDivComp() == StockCompareType.LESS))
        {
            matchingStocks = (ArrayList<Stock>) stockdao.findDividendLessThanValue(newSearch.getDividend());
            foundStocks = subQuery(matchingStocks, foundStocks);
        }

        model.addAttribute("stocks", foundStocks);
        model.addAttribute("title", "My Stocks");

        return "stock/index";
    }

 /**   @RequestMapping(value = "remove", method = RequestMethod.GET)
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
            stockDao.delete(stockId);
        }

        return "redirect:";
    } */

}
