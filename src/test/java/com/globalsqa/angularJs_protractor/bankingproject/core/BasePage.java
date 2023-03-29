package com.globalsqa.angularJs_protractor.bankingproject.core;

import org.openqa.selenium.WebDriver;

/**
 *Базовый класс для всех классов page
 */

public abstract class BasePage {
    protected static WebDriver driver;
    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }
}
