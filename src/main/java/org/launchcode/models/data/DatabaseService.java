package org.launchcode.models.data;

import org.launchcode.models.Stock;

import java.util.List;

public interface DatabaseService {

    public List<Stock> findDividend(double dividend, int compare);

    public void printName(int id);
}
