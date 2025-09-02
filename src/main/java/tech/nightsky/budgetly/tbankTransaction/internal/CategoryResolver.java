package tech.nightsky.budgetly.tbankTransaction.internal;

import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.category.DefaultCategoryCodes;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryResolver {
    private final Map<String, DefaultCategoryCodes> categoryHotMap = new HashMap<>();
    private final Map<String, DefaultCategoryCodes> nameAndCategoryHotMap = new HashMap<>();

    //Карта сопоставления категории Транзакции Т-Банк
    {
        categoryHotMap.put("еда", DefaultCategoryCodes.FOOD);
        categoryHotMap.put("фастфуд", DefaultCategoryCodes.FOOD);
        categoryHotMap.put("супермаркеты", DefaultCategoryCodes.MARKET);
        categoryHotMap.put("дом", DefaultCategoryCodes.HOME);
        categoryHotMap.put("досуг", DefaultCategoryCodes.FUN);
        categoryHotMap.put("здоровье", DefaultCategoryCodes.HEALTH);
        categoryHotMap.put("подписка", DefaultCategoryCodes.SUBSCRIPTIONS);
        categoryHotMap.put("жкх", DefaultCategoryCodes.UTILITIES);
        categoryHotMap.put("туризм", DefaultCategoryCodes.TOURISM);
        categoryHotMap.put("подарки", DefaultCategoryCodes.GIFTS);
        categoryHotMap.put("партнёр", DefaultCategoryCodes.PARTNER);
        categoryHotMap.put("другое", DefaultCategoryCodes.OTHER);
        categoryHotMap.put("транспорт", DefaultCategoryCodes.TRANSPORT);
        categoryHotMap.put("такси", DefaultCategoryCodes.TRANSPORT);
        categoryHotMap.put("одежда и обувь", DefaultCategoryCodes.TRANSPORT);
        categoryHotMap.put("развлечения", DefaultCategoryCodes.FUN);
        categoryHotMap.put("переводы", DefaultCategoryCodes.OTHER);
        categoryHotMap.put("дом и ремонт", DefaultCategoryCodes.HOME);
        categoryHotMap.put("рестораны", DefaultCategoryCodes.FOOD);
        categoryHotMap.put("мобильная связь", DefaultCategoryCodes.UTILITIES);
        categoryHotMap.put("спорттовары", DefaultCategoryCodes.FUN);
        categoryHotMap.put("ж/д билеты", DefaultCategoryCodes.TOURISM);
        categoryHotMap.put("местный транспорт", DefaultCategoryCodes.TRANSPORT);
        categoryHotMap.put("медицина", DefaultCategoryCodes.HEALTH);
    }

    //Карта сопоставления категории Транзакции Т-Банк и названия с категориями
    {
        categoryHotMap.put("самокаты в городе:развлечения", DefaultCategoryCodes.TRANSPORT);
        categoryHotMap.put("кафе:развлечения", DefaultCategoryCodes.FOOD);
        categoryHotMap.put("ym*hostkey:сервис", DefaultCategoryCodes.SUBSCRIPTIONS);
        categoryHotMap.put("ddx fitness:развлечения", DefaultCategoryCodes.HEALTH);
        categoryHotMap.put("яндекс сервисы:цифровые товары", DefaultCategoryCodes.SUBSCRIPTIONS);
        categoryHotMap.put("проценты на остаток:проценты", DefaultCategoryCodes.DEPOSITS);
        categoryHotMap.put("магнит косметик:красота", DefaultCategoryCodes.HOME);
        categoryHotMap.put("марина с.:переводы", DefaultCategoryCodes.PARTNER);
        categoryHotMap.put("ао нспк_b2c:пополнения", DefaultCategoryCodes.CASHBACK);
        categoryHotMap.put("зачисление:кэшбека", DefaultCategoryCodes.CASHBACK);
        categoryHotMap.put("нетмонет:сервис", DefaultCategoryCodes.FOOD);
    }

    DefaultCategoryCodes findCategoryCodesBy(TbankTransactionSummary transaction) {
        return nameAndCategoryHotMap.computeIfAbsent(
                ResolverUtils.getDescriptionTypeKey(transaction),
                _ -> categoryHotMap.getOrDefault(
                        ResolverUtils.getCategoryKey(transaction),
                        DefaultCategoryCodes.OTHER
                )
        );
    }
}
