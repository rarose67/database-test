package org.launchcode.models.data;

import org.launchcode.models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceBean implements DatabaseService
{
    private static DatabaseServiceBean instance;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private UserDao userDao;

    public static DatabaseServiceBean getInstance() {
        if (instance == null) {
            instance = new DatabaseServiceBean();
        }
        return instance;
    }

    public List<Stock> findDividend(double dividend, int compare)
    {
        if (compare == 1)
        {
            return stockDao.findDividendGreaterThanValue(dividend);
        }
        else
        {
            return stockDao.findDividendLessThanValue(dividend);
        }
    }

    public void printName(int id)
    {
        String name = userDao.findUsernameById(id);
        System.out.println("\nUser: " + name);
    }
}
