package com.example.minseop.midasit.model;

public enum AdministratorLevel {

    EMPLOYEE(0),
    ADMINISTRATOR(1),
    SUPERVISER(2);

    private final int value;

    AdministratorLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
