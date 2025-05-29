package tech.nightsky.budgetly.tbankTransaction;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record TbankTransactionSummary(
        Long id,
        Long tbankImportId,
        LocalDateTime operationDate,
        LocalDate paymentDate,
        String cardNumber,
        String status,
        BigDecimal operationAmount,
        String operationCurrency,
        BigDecimal paymentAmount,
        String paymentCurrency,
        BigDecimal cashback,
        String category,
        Integer mcc,
        String description,
        BigDecimal bonuses,
        BigDecimal investmentRounding,
        BigDecimal roundedOperationAmount,
        Integer rowHash
) {
}
