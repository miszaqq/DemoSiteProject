package com.twoitesting.POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getTextOfCart (){
        WebElement cartBody = driver.findElement(By.cssSelector(".woocommerce > form[method='post']"));
        return cartBody.getText();
    }
}
