package org.example.splitwisedec24.repos;

import org.example.splitwisedec24.models.Expense;
import org.example.splitwisedec24.models.Group;
import org.example.splitwisedec24.models.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Integer> {

    List<Expense> findAllByGroup_Id(int groupId);
}
