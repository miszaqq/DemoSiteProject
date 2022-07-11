package com.twoitesting.POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "main#main")
    private WebElement cartMainContent;

    public String getTextOfCart (){
        String cartContent = cartMainContent.getText();
        return cartContent;
    }

    @FindBy(css = "[aria-label='Remove this item']")
    private WebElement removeItemButton;
    public void removeItem(){
        removeItemButton.click();
    }


}
