package org.launchcode.models;

import org.launchcode.models.data.DatabaseServiceBean;
import org.launchcode.models.data.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.GregorianCalendar;

@Component
public class Test {

   @Autowired
   private StockDao stockDao;


    public void printSymbols()
    {
        System.out.println("\nHome Stocks:\n");
        for (Stock stock : stockDao.findDividendLessThanValue(1.5))
        {
            System.out.println("Symbol: " + stock.getSymbol() + ", " + stock.getName());
        }
    }
}
