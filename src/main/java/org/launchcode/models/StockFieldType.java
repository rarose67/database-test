package org.launchcode.models;

/**
 * Created by LaunchCode
 */
public enum StockFieldType {

    SYMBOL ("Symbol"),
    NAME ("Name"),
    PRICE ("Price"),
    DIVIDEND ("Dividend"),
    YIELD ("Yield"),
    DATE("Date");

    private final String name;

    StockFieldType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
