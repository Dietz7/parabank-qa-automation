package com.parabank.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.parabank.config.Config;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(Config.BASE_URL);
    }

    public void enterUserName(String username) {
        page.locator("input[name='username']").fill(username);
    }

    public void enterPassword(String password) {
        page.locator("input[name='password']").fill(password);
    }

    public void clickLogin() {
        page.locator("input[value='Log In']").click();
    }

    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLogin();
    }

    public Locator userNameField() {
        return page.locator("input[name = 'username']");
    }

    public Locator passwordField() {
        return page.locator("input[name='password']");
    }

    public Locator loginButton() {
        return page.locator("input[value='Log In']");
    }

    public Locator accountsOverviewLink() {
        return page.locator("a[href='overview.htm']");
    }

    public Locator loginErrorMessage() {
        return page.locator("p.error");
    }

}
