package org.example.splitwisedec24.dtos;

import org.example.splitwisedec24.models.Transaction;

import java.util.List;

public class SettleUpResponseDto {
    private List<Transaction> transactions;
    private ResponseStatus responseStatus;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
