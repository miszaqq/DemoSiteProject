package com.twoitesting.POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {

    private WebDriver driver;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Locators that will be reused later

        //Takes you to Shop using shop link in top menu (css)
    @FindBy(css = ".menu-item.menu-item-43.menu-item-object-page.menu-item-type-post_type > a")
    private WebElement mainMenuShop;

    @FindBy(css = ".menu-item.menu-item-44.menu-item-object-page.menu-item-type-post_type > a")
    private WebElement mainMenuCart;

    @FindBy(css = ".menu-item.menu-item-45.menu-item-object-page.menu-item-type-post_type > a")
    WebElement mainMenuCheckout;

    @FindBy(css = ".menu-item.menu-item-46.menu-item-object-page.menu-item-type-post_type > a")
    WebElement mainMenuMyaccount;

    //Service Methods

        //From Welcome Page go to Shop Page
    public void goToShop() throws InterruptedException {
        mainMenuShop.click();
        Thread.sleep(3000);
    }

        //From Welcome Page go to Cart Page
    public void goToCart(){
        mainMenuCart.click();
    }

        //From Welcome Page go to Checkout Page
    public void goToCheckout(){
        mainMenuCheckout.click();
    }


}
