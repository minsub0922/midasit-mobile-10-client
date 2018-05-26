package com.example.minseop.midasit.model;

import org.joda.time.DateTime;

import java.sql.Timestamp;

public class Account {

    private final int id;
    private final String employeeNumber;
    private final String username;
    private final int admin;
    private final Timestamp createdAt;
    private Timestamp lastLoginAttemptAt;
    private Timestamp lastLoggedInAt;

    public Account(int id, String employeeNumber, String username, int admin, DateTime createdAt) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.username = username;
        this.admin = admin;
        this.createdAt = new Timestamp(createdAt.toDate().getTime());
    }

    public int getId() {
        return id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getUsername() {
        return username;
    }

    public int getAdmin() {
        return admin;
    }

    public DateTime getLastLoginAttemptAt() {
        return new DateTime(lastLoginAttemptAt);
    }

    public DateTime getLastLoggedInAt() {
        return new DateTime(lastLoggedInAt);
    }

    public DateTime getCreatedAt() {
        return new DateTime(createdAt);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", employeeNumber='").append(employeeNumber).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", admin=").append(admin);
        sb.append(", lastLoginAttemptAt=").append(getLastLoginAttemptAt());
        sb.append(", lastLoggedInAt=").append(getLastLoggedInAt());
        sb.append(", createdAt=").append(getCreatedAt());
        sb.append('}');
        return sb.toString();
    }
}
