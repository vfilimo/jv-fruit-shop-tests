package core.basesyntax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.exception.FruitShopException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.TransactionParserService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransactionParserServiceImplTest {
    private final FruitTransaction expectedfruitTransaction =
            new FruitTransaction(FruitTransaction.Operation.BALANCE, "banana", 15);
    private final List<String> inputDataList = List.of("b, apple, 10", "s,apple,4");
    private final List<FruitTransaction> expectedTransactionList = List.of(
            new FruitTransaction(FruitTransaction.Operation.BALANCE, "apple", 10),
            new FruitTransaction(FruitTransaction.Operation.SUPPLY, "apple", 4));
    private TransactionParserService transactionParser;

    @BeforeEach
    void setUp() {
        transactionParser = new TransactionParserServiceImpl();
    }

    @Test
    void parseFromListStrings_Ok() {
        List<FruitTransaction> actualTransactionsList = transactionParser
                .parseFromListStrings(inputDataList);
        assertEquals(expectedTransactionList, actualTransactionsList);
    }

    @Test
    void parseFromString_Ok() {
        String inputData = "b, banana, 15";
        FruitTransaction actualfruitTransaction = transactionParser.parseFromString(inputData);
        assertEquals(expectedfruitTransaction, actualfruitTransaction);
    }

    @Test
    void parseFromString_incorrectString_notOk() {
        String invalidInputData = "b, banana, 15, cat";
        assertThrows(FruitShopException.class,
                () -> transactionParser.parseFromString(invalidInputData));
    }
}
