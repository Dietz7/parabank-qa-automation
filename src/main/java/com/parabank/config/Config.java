package com.parabank.config;

public class Config {
    public static final String TEST_ACCOUNT_ID = "13566";
    public static final String TEST_CUSTOMER_ID = "12434";
    public static final String NON_EXISTENT_ACCOUNT_ID = "999999";
    public static final String BASE_URL =
            System.getProperty("baseurl", "http://localhost:8080/parabank");
    public static final String API_BASE_URL =
            BASE_URL + "/services/bank";
    public static final String DATABASE_URL =
            System.getProperty(
                    "databaseurl",
                    "jdbc:hsqldb:hsql://localhost/parabank"
            );
    public static final String DATABASE_USERNAME =
            System.getProperty("databaseuser", "sa");
    public static final String DATABASE_PASSWORD =
            System.getProperty("databasepassword", "");

    public static void printEnvironment() {
        System.out.println("Testing against: " + BASE_URL);
    }
}

