package com.parabank.api;

import com.parabank.config.Config;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

public class TransactionApiTest {
    @Test
    void getAccountTransactions() {
        given()
                .pathParam("accountId", Config.TEST_ACCOUNT_ID)
                .when()
                .get(Config.API_BASE_URL + "/accounts/{accountId}/transactions")
                .then()
                .statusCode(200)
                .body("transactions", notNullValue());
    }

    @Test
    void getAccountTransactionsByAmount() {
        given()
                .pathParam("accountId", Config.TEST_ACCOUNT_ID)
                .pathParam("amount", "10")
                .when()
                .get(Config.API_BASE_URL + "/accounts/{accountId}/transactions/amount/{amount}")
                .then()
                .statusCode(200)
                .body("transactions.transaction.amount",
                        everyItem(equalTo("10.00"))
                );
    }
}
