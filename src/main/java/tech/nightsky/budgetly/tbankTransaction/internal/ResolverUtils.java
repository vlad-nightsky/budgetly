package tech.nightsky.budgetly.tbankTransaction.internal;

import tech.nightsky.budgetly.core.Constant;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;

public class ResolverUtils {
    static String getDescriptionTypeKey(TbankTransactionSummary tbankTransaction) {
        return tbankTransaction.description().toLowerCase(Constant.RUSSIA) + ":" + tbankTransaction.category().toLowerCase(Constant.RUSSIA);
    }

    static String getCategoryKey(TbankTransactionSummary tbankTransaction) {
        return tbankTransaction.category().toLowerCase(Constant.RUSSIA);
    }
}
