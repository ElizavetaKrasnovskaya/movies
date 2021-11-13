package com.bsuir.spring.model;

public enum EmployeeRole {
    DIRECTOR("Director"),
    ACTOR("Actor");

    private String name;

    EmployeeRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
