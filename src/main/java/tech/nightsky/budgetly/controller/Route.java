package tech.nightsky.budgetly.controller;

public class Route {
    //Общий домен
    public static final String API = "/api";
    public static final String V1 = API + "/v1";

    //Список ресурсов
    public static final String ACCOUNTS = V1 + "/accounts";
    public static final String CATEGORIES = V1 + "/categories";
    public static final String TRANSACTIONS = V1 + "/transactions";

    //Список путей в рамках ресурса
    public static final String ID = "/{id}";
}
