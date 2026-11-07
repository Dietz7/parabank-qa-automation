package com.parabank.ui;

import com.parabank.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParaBankSmokeTest extends BaseTest {



    @Test
    void paraBankPageLoads() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        assertEquals("ParaBank | Welcome | Online Banking", page.title());
    }


}
