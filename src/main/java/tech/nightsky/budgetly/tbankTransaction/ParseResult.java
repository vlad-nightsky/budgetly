package tech.nightsky.budgetly.tbankTransaction;

public enum ParseResult {
    PARSED,
    FILTERED,

    //todo возможно не будут использованы
    DUPLICATE,
    ERROR,
    FAILURE
}
