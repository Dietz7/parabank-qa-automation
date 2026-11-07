package com.parabank.ui;

import com.parabank.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountSmokeTest extends BaseTest {

    @Test
    void paraBankPageIsNotBlank() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        assertTrue(page.title().contains("ParaBank"));
    }
}
