package com.globalsqa.angularJs_protractor.bankingproject.pages;

import com.globalsqa.angularJs_protractor.bankingproject.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * ManagePage class
 * Делаем абстрактным для того чтобы локаторы были доступны классах наследниках, т.к. меню общее
 */

public abstract class ManagePage extends BasePage {
    //Кнопка перехода на страницу добавления клиента
    @FindBy(xpath = "//button[@ng-click='addCust()']")
    WebElement addCustomerButton;
    //Кнопка перехода на страницу списка клиентов
    @FindBy(xpath = "//button[@ng-click='showCust()']")
    WebElement customersButton;

    public ManagePage(){
        PageFactory.initElements(driver, this);
    }

    @Step("Метод клика по кнопке Add Customer")
    public AddCustomerPage clickAddCustomerButton(){
        addCustomerButton.click();
        return new AddCustomerPage();
    }

    @Step("Метод клика по кнопке Сustomers")
    public CustomersPage clickCustomersButton(){
        customersButton.click();
        return new CustomersPage();
    }
}
