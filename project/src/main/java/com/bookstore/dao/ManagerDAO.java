package com.bookstore.dao;

import com.bookstore.model.Manager;
import com.bookstore.utils.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {
    private static final String MANAGER_FILE = "managers.txt";

    public boolean addManager(Manager manager) {
        String data = manager.toString() + "\n";
        return FileHandler.writeToFile(MANAGER_FILE, true, data);
    }

    public Manager getManagerByUsername(String username) {
        String[] lines = FileHandler.readFromFile(MANAGER_FILE);
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                Manager manager = Manager.fromString(line);
                if (manager != null && manager.getUsername().equals(username)) {
                    return manager;
                }
            }
        }
        return null;
    }

    public List<Manager> getAllManagers() {
        List<Manager> managers = new ArrayList<>();
        String[] lines = FileHandler.readFromFile(MANAGER_FILE);
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                Manager manager = Manager.fromString(line);
                if (manager != null) {
                    managers.add(manager);
                }
            }
        }
        return managers;
    }

    // Initialize default manager if file doesn't exist
    public void initializeDefaultManager() {
        if (!FileHandler.isFileExist(MANAGER_FILE)) {
            Manager defaultManager = new Manager("1", "admin", "admin123", "System Administrator", 
                                                "admin@bookstore.com", "MGR001", "FULL");
            addManager(defaultManager);
        }
    }
}