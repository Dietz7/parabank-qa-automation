# ParaBank QA Automation

A Java-based test automation project for validating the ParaBank online banking application through UI, API,
database, and integration testing.

The project uses Java 17, Maven, JUnit 5, Playwright, and REST Assured. It aims to demonstrate practical
QA automation skills, maintainable test structure, and validation across multiple application layers.

## Tech Stack

- **Language:** Java 17
- **Build tool:** Maven
- **Test framework:** JUnit 5
- **UI automation:** Playwright
- **API testing:** REST Assured
- **Database testing:** JDBC
- **Database:** HSQLDB
- **Application under test:** ParaBank
- **Environment:** Docker

## API Test Coverage

The API tests currently cover:

- Retrieving an account by ID
- Handling requests for a non-existing account
- Retrieving a customer's accounts
- Retrieving account transactions
- Filtering transactions by amount
- Depositing money and verifying the balance increase
- Withdrawing money and verifying the balance decrease

## UI Test Coverage

- Verifying that the ParaBank application loads
- Verifying login page elements
- Successful login
- Login with invalid credentials
- Login with empty credentials

## Database Test Coverage

- Establishing a database connection
- Validating account records
- Checking account balance value

## Integration Test Coverage

- Comparing account balances returned by the API with values stored in the database

## Prerequisites

- Java 17 or later
- Maven
- Docker
- A running local ParaBank instance

## Running the Application

Start the local ParaBank Docker container and make sure the application is accessible at:
http://localhost:8080/parabank
The database must also be accessible using the configured JDBC connection settings.

## Running the Tests

Run the complete test suite from the project root:

```bash
mvn test
```

The ParaBank application must be running at:
`http://localhost:8080/parabank`

## Test Results

The test suite was executed successfully using Maven.

- **Tests run:** 13
- **Failures:** 0
- **Errors:** 0
- **Skipped:** 0

## Test Environment

By default, tests run against the local ParaBank instance at http://localhost:8080/parabank.

To run a UI test against the public instance, set these VM options in IntelliJ IDEA:

-ea -Dbaseurl=https://parabank.parasoft.com/parabank

The configured URL is printed to the test console.

