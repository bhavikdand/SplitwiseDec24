package org.example.splitwisedec24.strategies;

import org.example.splitwisedec24.models.Transaction;
import org.example.splitwisedec24.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {

    List<Transaction> settleUp(Map<User, Double> userFinalTotals);

}
