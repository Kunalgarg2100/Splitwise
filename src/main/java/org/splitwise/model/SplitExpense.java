package org.splitwise.model;

import java.util.List;

public interface SplitExpense {
    Expense getSplit(String paidBy, double amount, List<String> involvedUsers, List<Double> values );
    boolean validate(double amount, List<Double> values );
}
