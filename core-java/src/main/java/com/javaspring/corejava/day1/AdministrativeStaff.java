package com.javaspring.corejava.day1;

public class AdministrativeStaff extends Staff{

    protected String role;

    public AdministrativeStaff(String name, int id, String role) {
        super(name, id);
        this.role = role;
    }

    @Override
    public String getDetails() {
        return "Administrative Staff: " + this.name + ", ID: " + this.id + ", Role: " + this.role;
    }
}
