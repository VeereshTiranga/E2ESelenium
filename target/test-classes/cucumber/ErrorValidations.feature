
@tag
Feature: Add product from Ecommerce Application


Background:
Given I landed on the application

@tag1
Scenario Outline: Submit order for product

Given I logged in using userName <userName> and password <password>
Then "Incorrect email or password." error message is displayed

Examples:
      | userName               |   password     | productName |
      | rockybhai123@gmail.com |    Bhai@1234    | ZARA COAT 3 |
