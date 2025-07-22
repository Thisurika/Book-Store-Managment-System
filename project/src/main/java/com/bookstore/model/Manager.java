package com.bookstore.model;

public class Manager extends Cashier {
    private String managerId;
    private String accessLevel;

    // Default constructor
    public Manager() {
        super();
    }

    // Parameterized constructor
    public Manager(String id, String username, String password, String name, String email, 
                  String managerId, String accessLevel) {
        super(id, username, password, name, email);
        this.managerId = managerId;
        this.accessLevel = accessLevel;
    }

    // Getters
    public String getManagerId() {
        return managerId;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    // Setters
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String getRole() {
        return "MANAGER";
    }

    @Override
    public String toString() {
        return super.toString() + "," + managerId + "," + accessLevel;
    }

    // Static method to create Manager from string data
    public static Manager fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 7) {
            return new Manager(parts[0], parts[1], parts[2], parts[3], 
                             parts[4], parts[5], parts[6]);
        }
        return null;
    }
}