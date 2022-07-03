package com.twoitesting;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.round;
import static org.hamcrest.Matchers.*;

public class StepDefinitions {

    WebDriver driver;

    @Before //setting Webdriver before each scenario
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

            //open test website before each scenario
        driver.get("https://www.edgewordstraining.co.uk/demo-site/");
    }


    @After //closing browser after each scenario, so next one start with fresh window
    public void TearDown(){
        driver.quit();
    }


    @Given("I am on main page")
    public void i_am_on_main_page() {
        String welcomeTo = driver.findElement(By.cssSelector("main#main")).getText();
        MatcherAssert.assertThat(welcomeTo, containsString("Welcome to Edgewords e-commerce demo"));
    }

    @When("I go to My account page")
    public void i_go_to_my_account() {

        driver.findElement(By.cssSelector(".menu-item.menu-item-46.menu-item-object-page.menu-item-type-post_type > a"))
                .click();
        String welcomeTo = driver.findElement(By.cssSelector("[id='post-7']")).getText();
        MatcherAssert.assertThat(welcomeTo, containsString("Login"));


    }
    @When("I input login details")
    public void i_input_login_details() {

        driver.findElement(By.cssSelector("input#username"))
                .sendKeys("qehwgf+7cqeg1srwuez8@sharklasers.com");
        driver.findElement(By.cssSelector("input#password"))
                .sendKeys("ABCD1234abcd!!");
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    @Then("I should be logged in to my account")
    public void i_am_logged_in_to_my_account() {

        String bodyText = driver.findElement(By.cssSelector("body")).getText();
        MatcherAssert.assertThat(bodyText, containsString("Hello qehwgf7cqeg1srwuez8"));
        MatcherAssert.assertThat(bodyText, containsString("Logout"));
    }

    //Scenario #2

    @Given("I am logged into my account")
    public void i_am_logged_into() {

        driver.findElement(By.cssSelector(".menu-item.menu-item-46.menu-item-object-page.menu-item-type-post_type > a"))
                .click();
        driver.findElement(By.cssSelector("input#username"))
                .sendKeys("qehwgf+7cqeg1srwuez8@sharklasers.com");
        driver.findElement(By.cssSelector("input#password"))
                .sendKeys("ABCD1234abcd!!");
        driver.findElement(By.cssSelector("button[name='login']")).click();
        String bodyText = driver.findElement(By.cssSelector("body")).getText();
        MatcherAssert.assertThat(bodyText, containsString("Hello qehwgf7cqeg1srwuez8"));
        MatcherAssert.assertThat(bodyText, containsString("Logout"));
    }
    @Given("I am on shop page")
    public void i_am_on_shop_page() throws InterruptedException {

        driver.findElement(By.cssSelector(".menu-item.menu-item-43.menu-item-object-page.menu-item-type-post_type > a"))
                .click();
        String shopMain = driver.findElement(By.cssSelector("main#main")).getText();
        MatcherAssert.assertThat(shopMain, containsString("Shop"));
        MatcherAssert.assertThat(shopMain, containsString("Sort by"));
        Thread.sleep(1000);


    }
    @When("I add item {string} to cart")
    public void i_add_item_to_cart(String itemNumber) {
        String locat = ("[data-product_id='"+itemNumber+"']");
        WebElement add = driver.findElement(By.cssSelector(locat));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", add);

    }
    @Then("Item {string} is added to cart")
    public void item_is_added_to_cart(String itemName) throws InterruptedException {
        driver.findElement(By.cssSelector("ul#site-header-cart  a[title='View your shopping cart']")).click();

        Thread.sleep(2000); // 2.5s sleep to allow cart page to fully load

        String cartContent = driver.findElement(By.cssSelector("main"))
                .getText();
        MatcherAssert.assertThat(cartContent, containsString(itemName));

        driver.findElement(By.cssSelector("[aria-label='Remove this item']")).click();

        Thread.sleep(2000);


    }

    //Scenario #3

    @Given("I am on cart page with {string} added")
    public void i_am_on_cart_page_with_item_added(String itemID) throws InterruptedException {
        i_am_logged_into();
        i_am_on_shop_page();

        String locat = ("[data-product_id='"+itemID+"']");
        WebElement add = driver.findElement(By.cssSelector(locat));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", add);
        Thread.sleep(1500);

        driver.findElement(By.cssSelector("ul#site-header-cart  a[title='View your shopping cart']")).click();
        Thread.sleep(2000);



    }
    @When("I add coupon {string}")
    public void i_add_coupon(String string) throws InterruptedException {
        driver.findElement(By.cssSelector("input#coupon_code")).sendKeys("Edgewords");
        driver.findElement(By.cssSelector("button[name='apply_coupon']")).click();

        Thread.sleep(2500);
    }
    @Then("15percent discount given")
    public void discount_given() {
        String discountAmount = driver.findElement(By.cssSelector(".cart-discount.coupon-edgewords > td > .amount.woocommerce-Price-amount")).getText();
        String discountAmount2 = discountAmount.replace("£", "");
        double discountAmount3 = Double.parseDouble(discountAmount2);

        String subtotal = driver.findElement(By.cssSelector(".cart-subtotal > td > .amount.woocommerce-Price-amount")).getText();
        String subtotal2 = subtotal.replace("£", "");
        double subtotal3 = Double.parseDouble(subtotal2);


        double discountExpected = round(subtotal3 * 15/100, 2);

        MatcherAssert.assertThat(discountExpected, is(discountAmount3));
    }
    @Then("Total should be correct")
    public void total_should_be_correct() throws InterruptedException {

        String totalPounds = driver.findElement(By.cssSelector("strong > .amount.woocommerce-Price-amount")).getText();
        String total = totalPounds.replace("£", "");
        double totalAmount = Double.parseDouble(total);

        String discountAmount = driver.findElement(By.cssSelector(".cart-discount.coupon-edgewords > td > .amount.woocommerce-Price-amount")).getText();
        String discountAmount2 = discountAmount.replace("£", "");
        double discountAmount3 = Double.parseDouble(discountAmount2);

        String subtotal = driver.findElement(By.cssSelector(".cart-subtotal > td > .amount.woocommerce-Price-amount")).getText();
        String subtotal2 = subtotal.replace("£", "");
        double subtotal3 = Double.parseDouble(subtotal2);

        double expectedTotal = round(subtotal3 + 3.95 - discountAmount3, 2);

        System.out.println(expectedTotal + "vs." +totalAmount);
        MatcherAssert.assertThat(expectedTotal, is(totalAmount));
        Thread.sleep(1000);


        //Remove coupon, because it stays even if items are removed,
            //Need to removed to check on different items
        driver.findElement(By.cssSelector(".woocommerce-remove-coupon")).click();
        Thread.sleep(1500);

            //remove item from cart
        driver.findElement(By.cssSelector("[aria-label='Remove this item']")).click();
        Thread.sleep(1500);

    }

    @Given("I am on home page and I am logged in")
    public void i_am_on_home_page_and_i_am_logged_in() {



    }
    @When("User log out")
    public void user_log_out() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User is no longer logged in")
    public void user_is_no_longer_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
