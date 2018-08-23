package org.launchcode.models;

/**
 * Created by LaunchCode
 */
public enum CompareType {

    NONE (""),
    EQUAL ("="),
    LESS ("<"),
    GREATER (">");


    private final String value;

    CompareType(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }

}
