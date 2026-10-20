# SDET Practice Framework

## Purpose
I developed this framework for practicing the publishing webstore scenarios
It includes, 
1. accessing the publishing webstore using selenium webdriver for chrome
2. navigating through categories and counting the books in those categories

## Structure
Test files are stored in src/test/java -- > CategoryNavigationTest.java
Webstore HomePage locator details are stored in src/test/java/pages -- > HomePage.java
Config properties are stored in project root --> config.properties
Test reports are stored in target/surefire-reports
Test evidence screenshots are stored in target/screenshots

## How to Run
Pre setup: install Java 17, Maven and Chrome
mvn test
mvn test 2>&1 | grep -E "Running|Tests run"

## Features
Used features 
Selenium WebDriver, TestNG, Page Object Model, data-driven tests, screenshot on failure, externalized config file

## What I Learned Building It
- I learned how to inspect elements and how to find their unambiguous locators from the webpage using console
- I learned how to store and define locators in a single java file so that it is easier to maintain in case a locator fails to find element
- Learned to use TestNG's @DataProvider which supplies rows of data, and the test runs once per row. 
- Throwing exceptions to handle errors with specific messages, which can help fix the cause
- URLs and secrets should be stored in config files to keep required privacy and control over URL maintenance for example the same tests can be executed using parameterized URLs for different environments(tests, prod, dev) 
- I debugged a green build that ran zero tests - Surefire naming patterns — and now always verify test counts, not just build results 
- I traced a 'config read failure' that was actually markdown syntax copy-pasted into the URL — invisible characters in config values, found with cat -A.