package com.javaspring.corejava.day1;

import com.javaspring.corejava.day2.Advisable;

public class Professor extends Faculty implements Advisable {

    private String specialization;

    public Professor(String name, int id, String department, String specialization) {
        super(name, id, department);
        this.specialization = specialization;
    }

    @Override
    public String getDetails() {
        return "Professor: " + this.name + ", ID: " + this.id + ", Department: " + this.department + ", Specialization: " + this.specialization;
    }

    @Override
    public void advise() {
        System.out.println(this.name + " is advising students.");
    }
}
