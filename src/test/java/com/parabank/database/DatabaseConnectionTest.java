package com.parabank.database;

import com.parabank.config.Config;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {
    @Test
    void canConnectToParaBankDatabase() throws SQLException {
        String url = Config.DATABASE_URL;
        String username = Config.DATABASE_USERNAME;
        String password = Config.DATABASE_PASSWORD;

        try (Connection connection =
                     DriverManager.getConnection(url, username, password)) {

            assertTrue(connection.isValid(2));

            String sql = """
                    SELECT ID, CUSTOMER_ID, TYPE, BALANCE
                    FROM ACCOUNT
                    WHERE ID = ?
                    """;

            try (PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setInt(
                        1, Integer.parseInt(Config.TEST_ACCOUNT_ID)
                );

                try (ResultSet accounts = statement.executeQuery()) {
                    assertTrue(accounts.next(), "Expected account was not found");

                    assertEquals(
                            Integer.parseInt(Config.TEST_ACCOUNT_ID),
                            accounts.getInt("ID")
                    );

                    assertEquals(
                            Integer.parseInt(Config.TEST_CUSTOMER_ID),
                            accounts.getInt("CUSTOMER_ID")
                    );
                    BigDecimal databaseBalance = accounts.getBigDecimal("BALANCE");
                    assertNotNull(databaseBalance, "Account balance should not be null");
                    System.out.println("Account type: " + accounts.getString("TYPE"));
                }
            }
        }
    }
}
