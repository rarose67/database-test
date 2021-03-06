package org.launchcode.models;

/**
 * Created by LaunchCode
 */
public enum StockCompareType {

    NONE ("None"),
    EQUAL ("="),
    LESS ("<"),
    GREATER (">");


    private final String name;

    StockCompareType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
