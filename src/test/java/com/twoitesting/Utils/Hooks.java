package com.twoitesting.Utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    public static WebDriver driver;

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
}
