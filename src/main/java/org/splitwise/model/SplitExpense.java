package org.splitwise.model;

import java.util.List;

public interface SplitExpense {
    public Expense getSplit(String paidBy, double amount, List<String> involvedUsers, List<Double> values );
    public boolean validate(double amount, List<Double> values );
}
