package com.twoitesting.POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyaccountPage {

    private WebDriver driver;

    public MyaccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".woocommerce-MyAccount-navigation-link--customer-logout [href]")
    private WebElement Logout;

        // Logout method
    public void myAccountLogout(){
        Logout.click();
    }

    @FindBy(css = "input#username")
    private WebElement inputUsernameField;

    @FindBy(css = "input#password")
    private WebElement inputPasswordField;

    @FindBy(css = "button[name='login']")
    private WebElement logInButton;

        // Log in method
    public void myAccountLogIn(String username, String password){
        //inputUsernameField.sendKeys("qehwgf+7cqeg1srwuez8@sharklasers.com");
        //inputPasswordField.sendKeys("ABCD1234abcd!!");
        inputUsernameField.sendKeys(username);
        inputPasswordField.sendKeys(password);
        logInButton.click();
    }
}
