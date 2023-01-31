package org.splitwise.model;

import java.util.HashMap;
import java.util.List;

public class Expense {
    private final ExpenseType expenseType;
    private String paidBy;
    private final double amount;
    private final List<String> involvedUsers;
    private HashMap<String,Double> splits;

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public double getAmount() {
        return amount;
    }

    public List<String> getInvolvedUsers() {
        return involvedUsers;
    }

    public HashMap<String, Double> getSplits() {
        return splits;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setSplits(HashMap<String, Double> splits) {
        this.splits = splits;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public Expense(ExpenseType expenseType, String paidBy, double amount, List<String> involvedUsers, HashMap<String,Double> splits ) {
        this.expenseType = expenseType;
        this.paidBy = paidBy;
        this.amount = amount;
        this.involvedUsers = involvedUsers;
        this.splits = splits;
    }
}
