package com.example.expensetracker.service;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        log.info("Fetching all expenses");
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(String id) {
        log.info("Fetching expense with id: {}", id);
        return expenseRepository.findById(id);
    }

    public Expense addExpense(Expense expense) {
        log.info("Adding new expense with title: {}", expense.getTitle());
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(String id, Expense expense) {
        log.info("Updating expense with id: {}", id);
        expense.setId(id);
        return expenseRepository.save(expense);
    }

    public void deleteExpense(String id) {
        log.info("Deleting expense with id: {}", id);
        expenseRepository.deleteById(id);
    }

    public Double calculateTotalExpenses() {
        log.info("Calculating total expenses");
        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
