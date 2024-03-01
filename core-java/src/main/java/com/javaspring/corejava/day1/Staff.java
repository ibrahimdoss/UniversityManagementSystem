package com.javaspring.corejava.day1;

public abstract class Staff {

    protected String name;
    protected int id;

    public Staff(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract String getDetails();

}
