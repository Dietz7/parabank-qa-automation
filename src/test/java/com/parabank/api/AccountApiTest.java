package com.parabank.api;

import com.parabank.config.Config;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountApiTest {
    @Test
    void getAccountByIdReturnsSuccess() {
        Response response =
                given()
                        .pathParam("accountId", Config.TEST_ACCOUNT_ID)
                        .when()
                        .get(Config.API_BASE_URL + "/accounts/{accountId}");
        BigDecimal apiBalance = new BigDecimal(
                response.xmlPath().getString("account.balance")
        );
        assertNotNull(apiBalance, "API balance should not be null");
        response.then()
                .statusCode(200)
                .body("account.id", equalTo(Config.TEST_ACCOUNT_ID))
                .body("account.type", equalTo("CHECKING"));
    }

    @Test
    void getAccountNotFound() {
        given()
                .pathParam("accountId", Config.NON_EXISTENT_ACCOUNT_ID)
                .when()
                .get(Config.API_BASE_URL + "/accounts/{accountId}")
                .then()
                .statusCode(400)
                .body(equalTo("Could not find account #" + Config.NON_EXISTENT_ACCOUNT_ID));
    }

    @Test
    void getCustomerAccounts() {
        given()
                .pathParam("customerId", Config.TEST_CUSTOMER_ID)
                .when()
                .get(Config.API_BASE_URL + "/customers/{customerId}/accounts")
                .then()
                .statusCode(200)
                .body("accounts.account.id", equalTo(Config.TEST_ACCOUNT_ID))
                .body("accounts.account.customerId", equalTo(Config.TEST_CUSTOMER_ID));
    }
}
