package com.twoitesting.POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.twoitesting.Utils.Hooks.driver;

public class ShopPage {

    private WebDriver driver;

    public ShopPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

        //Locators on ShopPage to be reused below in methods
    public void addProductXX (String productID) {
        WebElement addXX = driver.findElement(By.cssSelector("[data-product_id='" + productID + "']"));
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("arguments[0].click();", addXX);
    }

}
