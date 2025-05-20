
@tag
Feature: Add product from Ecommerce Application


Background:
Given I landed on the application

@tag1
Scenario Outline: Submit order for product

Given I logged in using userName <userName> and password <password>
When I added the product <productName> to cart
And I checkout the product <productName>  and placed the order
Then "Thankyou for the order." message is displayed

Examples:
      | userName               |   password     | productName |
      | rockybhai123@gmail.com |    Bhai@123    | ZARA COAT 3 |
