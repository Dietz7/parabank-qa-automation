package com.parabank.ui;

import com.parabank.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    void userCanLoginSuccessfully() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        String username = System.getenv("PARABANK_USERNAME");
        String password = System.getenv("PARABANK_PASSWORD");

        assertNotNull(username, "PARABANK_USERNAME environment variable is missing");
        assertNotNull(password, "PARABANK_PASSWORD environment variable is missing");

        loginPage.login(username, password);

        assertThat(loginPage.accountsOverviewLink()).isVisible();
    }

    @Test
    void userCannotLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.login("Testerin", "just@1test");
        assertThat(loginPage.loginErrorMessage()).isVisible();
    }

    @Test
    void loginPageDisplaysRequiredElements() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.open();
        assertThat(loginPage.userNameField()).isVisible();
        assertThat(loginPage.passwordField()).isVisible();
        assertThat(loginPage.loginButton()).isVisible();
    }

    @Test
    void userCannotLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.login("", "");
        assertThat(loginPage.loginErrorMessage()).isVisible();
    }
}
