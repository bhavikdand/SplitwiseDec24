package org.example.splitwisedec24.utils;

import org.example.splitwisedec24.models.Expense;
import org.example.splitwisedec24.models.ExpenseType;
import org.example.splitwisedec24.models.ExpenseUser;
import org.example.splitwisedec24.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseUtils {

    public static Map<User, Double> condenseExpenses(List<Expense> expenses){
        Map<User, Double> userFinalTotal = new HashMap<>();
        for(Expense expense: expenses){
            for (ExpenseUser expenseUser: expense.getExpenseUsers()){
                User user = expenseUser.getUser();
                double amount = expenseUser.getExpenseType() == ExpenseType.PAID ?
                        expenseUser.getAmount() : expenseUser.getAmount() * (-1);  // Ternary operator in java
                userFinalTotal.put(user, userFinalTotal.getOrDefault(user, 0.0d) + amount);
            }
        }
        return userFinalTotal;
    }
}
