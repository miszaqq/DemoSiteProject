package com.twoitesting.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    public static WebDriver driver;

    @Before ("@BasicBefore")//setting Webdriver before each scenario
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //open test website before each scenario
        driver.get("https://www.edgewordstraining.co.uk/demo-site/");
    }

    @Before("@EmptyCart")

    public void SetUpEmptyCart() throws InterruptedException {
            //@Before@EmptyCart makes sure that the cart is empty and voucher isn't applied
            //Otherwise it causes problems while checking for item in cart, or when using voucher
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.edgewordstraining.co.uk/demo-site/");
        driver.findElement(By.cssSelector(".menu-item.menu-item-46.menu-item-object-page.menu-item-type-post_type > a")).click();
            //Then check if user is logged in, should Logout and Log out be visible if logged in

        String myAccount = driver.findElement(By.cssSelector("main")).getText();
        Boolean amIloggedIn = myAccount.contains("Logout");

        if (amIloggedIn) {
            //Do nothing, proceed to next step

        }
        else {
            //If FALSE (not loggedin), then login user, to allow checking if cart is empty and/or voucher applied
            driver.findElement(By.cssSelector("input#username"))
                    .sendKeys("qehwgf+7cqeg1srwuez8@sharklasers.com");
            driver.findElement(By.cssSelector("input#password"))
                    .sendKeys("ABCD1234abcd!!");
            driver.findElement(By.cssSelector("button[name='login']")).click();

        }
            //Navigate to SHOP
        driver.findElement(By.cssSelector(".menu-item.menu-item-43.menu-item-object-page.menu-item-type-post_type > a")).click();
            //add 'Beanie' or any item to cart
        WebElement add = driver.findElement(By.cssSelector("[data-product_id='27']"));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", add);
        Thread.sleep(500);
            //Navigate to CART to check if voucher is applied
        driver.findElement(By.cssSelector(".menu-item.menu-item-44.menu-item-object-page.menu-item-type-post_type > a")).click();

        String checkVoucher = driver.findElement(By.cssSelector(".calculated_shipping.cart_totals")).getText();

        Boolean isVoucherApplied = checkVoucher.contains("Coupon:");

        if (isVoucherApplied) {
            driver.get("https://www.edgewordstraining.co.uk/demo-site/cart/?remove_coupon=edgewords");
        }

            //Now to remove all item from basket we use Do/While Loop until basket is empty
        String cartEmpty = driver.findElement(By.cssSelector("main")).getText();

        Boolean isCartEmpty = cartEmpty.contains("Your cart is currently empty");

        if (!isCartEmpty) {
            Boolean isCartEmpty2;
            do {
                driver.findElement(By.cssSelector(".remove")).click();
                Thread.sleep(1500);
                String cartEmpty2 = driver.findElement(By.cssSelector("main")).getText();
                isCartEmpty2 = cartEmpty2.contains("Your cart is currently empty");
            }
            while (!isCartEmpty2);

        }
            //Now we should be sure that the cart is empty and no voucher is applied!
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/customer-logout/");
        driver.findElement(By.cssSelector("div[role='alert'] > a")).click();
        Thread.sleep(1000);

    }

    @After //closing browser after each scenario, so next one start with fresh window
    public void TearDown(){
        driver.quit();
    }
}
