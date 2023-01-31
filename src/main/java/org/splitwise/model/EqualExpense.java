package org.splitwise.model;

import java.util.HashMap;
import java.util.List;

public class EqualExpense implements SplitExpense{
    private final ExpenseType expenseType = ExpenseType.EQUAL;
    public Expense getSplit(String paidBy, double amount, List<String> involvedUsers, List<Double> values) {
        HashMap<String,Double> splits = new HashMap<String,Double>();
        double splitAmount = amount / involvedUsers.size();
        double remAmount = 0;
        for (String u : involvedUsers) {
            if (u != paidBy) {
                remAmount = remAmount + splitAmount;
                splits.put(u, splitAmount);
            }
        }
        if (amount - remAmount > 0)
            splits.put(paidBy, amount - remAmount);
        return new Expense( expenseType, paidBy, amount, involvedUsers, splits);
    }

    public boolean validate(double amount, List<Double> values) {
        return true;
    }
}
