package com.parabank.integration;

import com.parabank.config.Config;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class AccountBalanceIntegrationTest {
    @Test
    void apiBalanceMatchesDatabaseBalance() throws Exception {
        String apiBalanceText =
                given()
                        .pathParam("accountId", Config.TEST_ACCOUNT_ID)
                        .when()
                        .get(Config.API_BASE_URL + "/accounts/{accountId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("account.balance")
                        .toString();

        BigDecimal apiBalance = new BigDecimal(apiBalanceText);

        assertNotNull(apiBalance, "API balance should not be null");

        try (Connection connection = DriverManager.getConnection(
                Config.DATABASE_URL,
                Config.DATABASE_USERNAME,
                Config.DATABASE_PASSWORD)) {
            String sql = """
                    SELECT BALANCE
                    FROM ACCOUNT
                    WHERE ID = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(
                        1, Integer.parseInt(Config.TEST_ACCOUNT_ID)
                );
                try (ResultSet resultSet = statement.executeQuery()) {
                    assertTrue(resultSet.next(),
                            "Expected account was not found in the database");

                    BigDecimal databaseBalance = resultSet.getBigDecimal("BALANCE");
                    assertNotNull(databaseBalance, "Database balance should not be null");
                    assertEquals(
                            0,
                            apiBalance.compareTo(databaseBalance),
                            "API and database balances should match"
                    );
                }
            }
        }
    }
}
