package tech.nightsky.budgetly.tbankTransaction.internal;

import org.springframework.stereotype.Component;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;

import java.util.HashSet;
import java.util.Set;

@Component
public class FilterResolver {
    private final Set<String> filter = new HashSet<>();

    //Карта категорий и названий Транзакции Т-Банк, которые нужно пропускать
    {
        filter.add("между своими счетами:переводы");
        filter.add("владислав к.:переводы");
        filter.add("перевод для закрытия накопительного счёта:переводы");
        filter.add("перевод кэшбэка:переводы");
        filter.add("перевод кэшбэка:пополнения");
        filter.add("аноним:переводы");
    }

    public boolean isFiltered(TbankTransactionSummary tbankTransaction) {
        return filter.contains(ResolverUtils.getDescriptionTypeKey(tbankTransaction));
    }
}
