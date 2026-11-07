package com.parabank.api;

import com.parabank.config.Config;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WithdrawApiTest {

    private static final BigDecimal WITHDRAW_AMOUNT =
            new BigDecimal("10.00");

    @Test
    void withdrawFromAccount() {
        Response balanceBeforeResponse =
                given()
                        .pathParam("accountId", Config.TEST_ACCOUNT_ID)
                        .when()
                        .get(Config.API_BASE_URL + "/accounts/{accountId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        BigDecimal balanceBefore =
                new BigDecimal(
                        balanceBeforeResponse.xmlPath().getString("account.balance")
                );

        given()
                .queryParam("accountId", Config.TEST_ACCOUNT_ID)
                .queryParam("amount", WITHDRAW_AMOUNT.toPlainString())
                .when()
                .post(Config.API_BASE_URL + "/withdraw")
                .then()
                .statusCode(200);

        Response balanceAfterResponse =
                given()
                        .pathParam("accountId", Config.TEST_ACCOUNT_ID)
                        .when()
                        .get(Config.API_BASE_URL + "/accounts/{accountId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        BigDecimal balanceAfter =
                new BigDecimal(
                        balanceAfterResponse.xmlPath().getString("account.balance")
                );

        assertEquals(
                0,
                balanceBefore.subtract(WITHDRAW_AMOUNT).compareTo(balanceAfter),
                "Account balance should decrease by the withdrawn amount"
        );
    }
}
