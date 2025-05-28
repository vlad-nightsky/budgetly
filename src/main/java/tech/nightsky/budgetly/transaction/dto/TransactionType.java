package tech.nightsky.budgetly.transaction.dto;

import org.springframework.modulith.NamedInterface;

/**
 * Тип транзакции (доход или расход).
 */
@NamedInterface("transaction")
public enum TransactionType {
    EXPENSE,  // Расход
    INCOME    // Доход
}
