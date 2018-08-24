package org.launchcode.models;

import org.launchcode.models.data.DatabaseService;
import org.launchcode.models.data.DatabaseServiceBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.GregorianCalendar;

@Entity
public class Stock {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String symbol;

    @NotNull
    private String name;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @Min(0)
    private double dividend;

    @NotNull
    @Min(0)
    private double yield;

    @NotNull
    private GregorianCalendar lastDividendDate;

    @Transient
    private double weekStartPrice;


    public Stock() {
        this.lastDividendDate = (GregorianCalendar) GregorianCalendar.getInstance();
        this.weekStartPrice = 0;
    }

    public Stock(String aSymbol, String aName, double aDividend) {
        this();
        this.symbol = aSymbol;
        this.name = aName;
        this.dividend = aDividend;
    }

    public Stock(String aSymbol, String aName, double aPrice, double aDividend,
                 GregorianCalendar aLastDividendDate, double aWeekStartPrice) {
        this.symbol = aSymbol;
        this.name = aName;
        this.price = aPrice;
        this.dividend = aDividend;
        this.lastDividendDate = aLastDividendDate;
        this.weekStartPrice = aWeekStartPrice;
        this.yield = ((aDividend/aPrice) * 100);
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String aSymbol) {
        this.symbol = aSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double aPrice) {
        this.price = aPrice;
    }

    public double getDividend() {
        return dividend;
    }

    public void setDividend(double aDividend) {
        this.dividend = aDividend;
    }

    public GregorianCalendar getLastDividendDate() {
        return lastDividendDate;
    }

    public String showDate()
    {
        String show = "";

        show += (lastDividendDate.get(GregorianCalendar.MONTH)+1);
        show += '/';
        show += lastDividendDate.get(GregorianCalendar.DAY_OF_MONTH);
        show += '/';
        show += lastDividendDate.get(GregorianCalendar.YEAR);

        return show;
    }

    public void setLastDividendDate(GregorianCalendar aLastDividendDate) {
        this.lastDividendDate = aLastDividendDate;
    }

    public double getWeekStartPrice() {
        return weekStartPrice;
    }

    public void setWeekStartPrice(double aWeekStartPrice) {
        this.weekStartPrice = aWeekStartPrice;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double aYield) {
        this.yield = aYield;
    }

    public static void printSymbols()
    {
        DatabaseServiceBean dsBean = DatabaseServiceBean.getInstance();

        System.out.println("\nStocks:\n");
        for (Stock stock : dsBean.findDividend(1.5, -1))
        {
            System.out.println("Symbol: " + stock.getSymbol() + ", " + stock.getName());
        }
    }
}
