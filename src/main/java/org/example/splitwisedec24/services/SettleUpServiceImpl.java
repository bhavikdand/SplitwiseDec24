package org.example.splitwisedec24.services;

import org.example.splitwisedec24.models.*;
import org.example.splitwisedec24.repos.GroupExpenseRepository;
import org.example.splitwisedec24.strategies.SettleUpStrategy;
import org.example.splitwisedec24.utils.ExpenseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettleUpServiceImpl implements SettleUpService {

    private GroupExpenseRepository groupExpenseRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpServiceImpl(GroupExpenseRepository groupExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    @Override
    public List<Transaction> settleGroup(int groupId) {
        /*
        1. Fetch list of expenses from GroupExpense table
         */
        List<Expense> expenses = this.groupExpenseRepository.findAllByGroup_Id(groupId);
        Map<User, Double> userFinalTotal = ExpenseUtils.condenseExpenses(expenses);
        return settleUpStrategy.settleUp(userFinalTotal);
    }


}
