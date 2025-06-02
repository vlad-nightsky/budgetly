package tech.nightsky.budgetly.core.api;

public class Route {
    //Общий домен
    public static final String API = "/api";
    public static final String V1 = API + "/v1";

    //Список ресурсов
    public static final String ACCOUNTS = V1 + "/accounts";
    public static final String CATEGORIES = V1 + "/categories";
    public static final String TRANSACTIONS = V1 + "/transactions";
    public static final String IMPORT = V1 + "/import";
    public static final String TBANK_IMPORT = V1 + "/tbankImports";
    public static final String TBANK_TRANSACTIONS = V1 + "/tbankTransactions";

    //Список путей в рамках ресурса
    public static final String ID = "/{id}";
    public static final String CSV = "/csv";
}
