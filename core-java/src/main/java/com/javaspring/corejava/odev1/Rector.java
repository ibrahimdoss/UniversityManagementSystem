package com.javaspring.corejava.odev1;

import com.javaspring.corejava.odev2.Manageable;

public class Rector extends AdministrativeStaff implements Manageable {

    private int yearsOfService;

    public Rector(String name, int id, int yearsOfService) {
        super(name, id, "Rector");
        this.yearsOfService = yearsOfService;
    }

    @Override
    public String getDetails() {
        return "Rector: " + this.name + ", ID: " + this.id + ", Years of Service: " + this.yearsOfService;
    }

    @Override
    public void manage() {
        System.out.println(this.name + " is managing the university.");
    }
}
