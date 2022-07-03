Feature: Demo Site e-commerce
  @BasicBefore
  Scenario: Login to ecommerce site
    Given I am on main page
    And I go to My account page
    When I input login details
    Then I should be logged in to my account

  @EmptyCart @Run
  Scenario Outline: Enter shop and add item
    Given I am logged into my account
    And I am on shop page
    When I add item "<item number>" to cart
    Then Item "<item name>" is added to cart
    Examples:
      | item number | item name  |
      | 27          | Beanie     |
#      | 28          | Belt       |
#      | 29          | Cap        |
#      | 34          | Hoodie     |
#      | 30          | Sunglasses |

  @EmptyCart
  Scenario Outline: Apply coupon and check total
    Given I am logged into my account
    And I am on shop page
    And I am on cart page with "<item>" added
    When  I add coupon 'Edgewords'
    Then  15percent discount given
    And Total should be correct
    Examples:
    Examples:
      | item | name               |
      | 27   | Beanie             |
      | 28   | Belt               |
#      | 29   | Cap                |
#      | 30   | Sunglasses         |
#      | 34   | Hoodie             |
#      | 38   | Vneck Tshirt       |
#      | 37   | Tshirt             |
#      | 36   | Polo               |
#      | 35   | Long Sleeve Tee    |
#      | 33   | Hoodie with Zipper |
#      | 32   | Hoodie with Pocket |
#      | 31   | Hoodie with Logo   |

  @BasicBefore
  Scenario: Log out
    Given I am on home page and I am logged in
    When User log out
    Then User is no longer logged in

