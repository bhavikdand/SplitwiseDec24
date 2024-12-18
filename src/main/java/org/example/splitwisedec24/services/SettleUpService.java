package org.example.splitwisedec24.services;

import org.example.splitwisedec24.models.Transaction;

import java.util.List;

public interface SettleUpService {

    public List<Transaction> settleGroup(int groupId);
}
