package com.java;

import helper.ExcelWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;


public class LoadBrowser {
    public static WebDriver driver ;

    @BeforeSuite(alwaysRun = true)
    public void initChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\longcoin\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void destroyBrowser(){
        driver.close();
        try {
            new ExcelWriter().wirteExcel("TestCase");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
