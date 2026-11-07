package com.parabank.ui;

import com.microsoft.playwright.*;
import com.parabank.config.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    protected Page page;

    @BeforeEach
    void setUp() {
        Config.printEnvironment();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        context = browser.newContext();
        page = context.newPage();
    }

   @AfterEach
    void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
