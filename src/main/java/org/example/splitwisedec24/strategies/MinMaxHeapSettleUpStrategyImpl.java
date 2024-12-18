package org.example.splitwisedec24.strategies;

import org.example.splitwisedec24.models.Pair;
import org.example.splitwisedec24.models.Transaction;
import org.example.splitwisedec24.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

@Component
public class MinMaxHeapSettleUpStrategyImpl implements SettleUpStrategy{

    @Override
    public List<Transaction> settleUp(Map<User, Double> userFinalTotals) {
        /*
        1. Initialize our heaps
        2. Iterate over the map, put all -ve values in minHeap and +ve ones in maxHeap
        2.1 List<transaction> txns = new ArrayList<>()
        3. while(both heaps are not empty)
        3.1     Pair<User, Amount> personToPay = minHeap.poll()
        3.2     Pair<User, Amount> personToReceive = maxHeap.poll()
        3.3     double txnAmount = Math.min(Math.abs(persontoPay.getAmount(),
                        personToReceive.getAmount())
        3.4     Transaction txn = new Transaction(personToPay.getUSer(), personToReceive.getUSer(), amount)
        3.5     txns.add(txn)
        3.6     if(personToPay.getAmount() + amount != 0)
        3.7         minHeap.add(new Pair<>(personToPay.getUser(),
                            personToPay.getAmount() + amount))

         */
        PriorityQueue<Pair<User, Double>> minHeap = new PriorityQueue<>(
                (a,b) -> (int) (a.getValue() - b.getValue())
        );
        PriorityQueue<Pair<User, Double>> maxHeap = new PriorityQueue<>(
                (a,b) -> (int) (b.getValue() - a.getValue())
        );

        for (Map.Entry<User, Double> userDoubleEntry : userFinalTotals.entrySet()) {
            if(userDoubleEntry.getValue() < 0){
                minHeap.add(new Pair<>(userDoubleEntry.getKey(), userDoubleEntry.getValue()));
            }
            else {
                maxHeap.add(new Pair<>(userDoubleEntry.getKey(), userDoubleEntry.getValue()));
            }
        }

        List<Transaction> txns = new ArrayList<>();

        while (!minHeap.isEmpty() && !maxHeap.isEmpty()){
            Pair<User, Double> personToPay = minHeap.poll();
            Pair<User, Double> personToReceive = maxHeap.poll();
            double amount = Math.min(Math.abs(personToPay.getValue()), personToReceive.getValue());
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setPaidFrom(personToPay.getKey());
            transaction.setPaidTo(personToPay.getKey());

            txns.add(transaction);

            if(personToPay.getValue() + amount != 0){
                minHeap.add(new Pair<>(personToPay.getKey(), personToPay.getValue() + amount));
            }

            if(personToReceive.getValue() - amount != 0){
                maxHeap.add(new Pair<>(personToReceive.getKey(), personToReceive.getValue() - amount));
            }

        }


        return txns;
    }
}
