package com.bookstore.dao;

import com.bookstore.model.CashierEmployee;
import com.bookstore.utils.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class CashierDAO {
    private static final String CASHIER_FILE = "cashiers.txt";

    public boolean addCashier(CashierEmployee cashier) {
        String data = cashier.toString() + "\n";
        return FileHandler.writeToFile(CASHIER_FILE, true, data);
    }

    public List<CashierEmployee> getAllCashiers() {
        List<CashierEmployee> cashiers = new ArrayList<>();
        String[] lines = FileHandler.readFromFile(CASHIER_FILE);
        
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                CashierEmployee cashier = CashierEmployee.fromString(line);
                if (cashier != null) {
                    cashiers.add(cashier);
                }
            }
        }
        return cashiers;
    }

    public CashierEmployee getCashierByUsername(String username) {
        List<CashierEmployee> cashiers = getAllCashiers();
        for (CashierEmployee cashier : cashiers) {
            if (cashier.getUsername().equals(username)) {
                return cashier;
            }
        }
        return null;
    }

    public boolean updateCashier(CashierEmployee updatedCashier) {
        List<CashierEmployee> cashiers = getAllCashiers();
        StringBuilder data = new StringBuilder();
        
        for (CashierEmployee cashier : cashiers) {
            if (cashier.getId().equals(updatedCashier.getId())) {
                data.append(updatedCashier.toString()).append("\n");
            } else {
                data.append(cashier.toString()).append("\n");
            }
        }
        
        return FileHandler.writeToFile(CASHIER_FILE, false, data.toString());
    }

    public boolean deleteCashier(String cashierId) {
        List<CashierEmployee> cashiers = getAllCashiers();
        StringBuilder data = new StringBuilder();
        
        for (CashierEmployee cashier : cashiers) {
            if (!cashier.getId().equals(cashierId)) {
                data.append(cashier.toString()).append("\n");
            }
        }
        
        return FileHandler.writeToFile(CASHIER_FILE, false, data.toString());
    }
}