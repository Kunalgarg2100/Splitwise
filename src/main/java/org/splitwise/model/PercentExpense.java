package org.splitwise.model;

import java.util.HashMap;
import java.util.List;

public class PercentExpense implements SplitExpense {
    private final ExpenseType expenseType = ExpenseType.PERCENT;
    public Expense getSplit(String paidBy, double amount, List<String> involvedUsers, List<Double> values ){

        HashMap<String,Double> splits = new HashMap<>();
        for (int i = 0; i < involvedUsers.size(); i++) {
            splits.put(involvedUsers.get(i), amount * values.get(i) * 0.01 );
        }
        return new Expense( expenseType, paidBy, amount, involvedUsers, splits );
    }

    public boolean validate(double amount, List<Double> values) {
        return true;
    }
}
