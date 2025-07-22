package com.bookstore.model;

public class CashierEmployee extends Cashier {
    private String employeeId;
    private String department;

    // Default constructor
    public CashierEmployee() {
        super();
    }

    // Parameterized constructor
    public CashierEmployee(String id, String username, String password, String name, String email, 
                          String employeeId, String department) {
        super(id, username, password, name, email);
        this.employeeId = employeeId;
        this.department = department;
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    // Setters
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getRole() {
        return "CASHIER";
    }

    @Override
    public String toString() {
        return super.toString() + "," + employeeId + "," + department;
    }

    // Static method to create CashierEmployee from string data
    public static CashierEmployee fromString(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 7) {
            return new CashierEmployee(parts[0], parts[1], parts[2], parts[3], 
                                     parts[4], parts[5], parts[6]);
        }
        return null;
    }
}