Feature: Demo Site e-commerce

  Scenario: Login to ecommerce site
    Given I am on main page
    And I go to My account page
    When I input login details
    Then I am logged in to my account


  Scenario Outline: Enter shop and add item
    Given I am logged into my account
    And I am on shop page
    When I add item "<item number>" to cart
    Then Item "<item name>" is added to cart
    Examples:
      | item number | item name  |
      | 27          | Beanie     |
      | 28          | Belt       |
      | 29          | Cap        |
      | 34          | Hoodie     |
      | 30          | Sunglasses |


#  Scenario: Apply coupon and check total
#    Given I am on cart page
#    When  I add coupon 'Edgewords'
#    Then  15% discount given
#    And Total should be correct
#
#
#  Scenario: Log out
#    Given I am on cart page
#    When I log out
#    Then User is no longer logged in

