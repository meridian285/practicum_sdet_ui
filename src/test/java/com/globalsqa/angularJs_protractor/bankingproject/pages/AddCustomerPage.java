package com.globalsqa.angularJs_protractor.bankingproject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

/**
 * Add Customer class
 */

public class AddCustomerPage extends ManagePage {
    //Локатор поля ввода First Name
    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;
    //Локатор поля ввода Last Name
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;
    //Локатор поля ввода PostCode
    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCodeField;
    //Локатор кнопки Add Customer
    @FindBy(xpath = "//button[text()='Add Customer']")
    private WebElement addCustomerButton;
    //Локатор формы заполнения полей
    @FindBy(xpath = "//form[@name='myForm']")
    private WebElement formFields;

    public AddCustomerPage(){
        PageFactory.initElements(driver,this);
    }

    @Step("Метод ввода значений в поля FirstName, LastName, PostCode")
    public void createCustomer(String firstname, String lastName, String postCode) {
        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastName);
        postCodeField.sendKeys(postCode);
        addCustomerButton.click();
    }

    @Step("Проверка сообщения о создании клиента, после проверки жмем ОК. Для параметризованного теста")
    public boolean checkMessageCreateCustomerParam() {
        return driver.switchTo().alert().getText().contains("Customer added successfully with customer id :");
    }
    @Step("Проверка что одно из полей не заполнено")
    public boolean checkingThatFieldsNotFilled(){
        return Objects.requireNonNull(formFields.getAttribute("class"))
                .contains("ng-invalid ng-invalid-required");
    }
}
