package org.splitwise;

import org.splitwise.api.ExpenseManager;
import org.splitwise.exception.ExactSplitException;
import org.splitwise.model.ExpenseType;
import org.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ExactSplitException {
        ExpenseManager expenseManager = ExpenseManager.getINSTANCE();
        expenseManager.addUser( "u1", "Kunal", "KG@email.com", 82734982 );
        expenseManager.addUser( "u2", "Muskan", "MJ@email.com", 82734932 );

        List<String> list=new ArrayList<String>();
        //Adding elements in the List
        list.add("u1");
        list.add("u2");

        List<Double> list1 =new ArrayList<Double>();
        //Adding elements in the List
        list1.add(125.0);
        list1.add(175.0);

        expenseManager.addExpense( ExpenseType.EXACT, "u1", 300, list, list1 );
        expenseManager.showExpense();
        System.out.println("Hello world!");
    }
}