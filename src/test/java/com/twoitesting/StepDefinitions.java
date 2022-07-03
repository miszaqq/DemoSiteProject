package com.twoitesting;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions.*;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.Matchers.containsString;

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

    @Then("I am logged in to my account")
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
}
