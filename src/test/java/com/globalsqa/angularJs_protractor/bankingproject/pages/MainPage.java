package com.globalsqa.angularJs_protractor.bankingproject.pages;

import com.globalsqa.angularJs_protractor.bankingproject.core.BasePage;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * ManePage class
 */

public class MainPage extends BasePage {
    //Локатор кнопки ManagerLoginButton
    @FindBy(xpath = "//button[text()='Bank Manager Login']")
    private WebElement bankManagerLoginButton;

    public MainPage(){
        driver.get("http://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        PageFactory.initElements(driver, this);
    }
    @Step("Метод нажатия на кнопку Bank Manager Login")
    public void clickBankManagerLoginButton(){
        bankManagerLoginButton.click();
    }
}
