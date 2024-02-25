package com.javaspring.corejava.odev1;

public abstract class Staff {

    protected String name;
    protected int id;

    public Staff(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract String getDetails();

}
