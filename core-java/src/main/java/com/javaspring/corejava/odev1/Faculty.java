package com.javaspring.corejava.odev1;

import com.javaspring.corejava.odev2.Researchable;
import com.javaspring.corejava.odev2.Teachable;

public class Faculty extends Staff implements Teachable, Researchable {

    protected String department;

    public Faculty(String name, int id, String department) {
        super(name, id);
        this.department = department;
    }

    @Override
    public String getDetails() {
        return "Faculty: " + this.name + ", ID: " + this.id + ", Department: " + this.department;
    }

    @Override
    public void teach() {
        System.out.println(this.name + " is teaching.");
    }

    @Override
    public void research() {
        System.out.println(this.name + " is conducting research.");
    }
}
