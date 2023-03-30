package com.globalsqa.angularJs_protractor.bankingproject.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;
import static org.junit.jupiter.api.AssertionFailureBuilder.assertionFailure;

/**
 * Add Customer class
 */

public class AddCustomerPage extends ManagePage {
    //Локатор поля ввода First Name
    @FindBy(xpath = "//*[@placeholder='First Name']")
    private WebElement firstNameField;
    //Локатор поля ввода Last Name
    @FindBy(xpath = "//*[@placeholder='Last Name']")
    private WebElement lastNameField;
    //Локатор поля ввода PostCode
    @FindBy(xpath = "//*[@placeholder='Post Code']")
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
    public AddCustomerPage createCustomer(String firstname, String lastName, String postCode) {
        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastName);
        postCodeField.sendKeys(postCode);
        addCustomerButton.click();
        return this;
    }

    @Step("Проверка сообщения о создании клиента, после проверки жмем ОК")
    public AddCustomerPage checkMessageCreateCustomer(){
        String actualAlertMessage= driver.switchTo().alert().getText();
        Assertions.assertTrue(actualAlertMessage.contains("Customer added successfully with customer id :"),
                "Сообщение подтверждения создания клиента не корректно или отсутствует");
        driver.switchTo().alert().accept();
        return this;
    }

    @Step("Проверка сообщения о создании клиента, после проверки жмем ОК. Для параметризованного теста")
    public void checkMessageCreateCustomerParam(boolean condition) {
        boolean actualAlertMessage= driver.switchTo().alert().getText().contains("Customer added successfully with customer id :");
        if(actualAlertMessage != condition){
            assertionFailure().message("Сообщение подтверждения создания клиента не корректно или отсутствует")
                    .expected(true).actual(false).buildAndThrow();
        }

        driver.switchTo().alert().accept();
    }

    @Step("Проверка что одно из полей не заполнено")
    public void checkingThatFieldsNotFilled(){
        boolean check = Objects.requireNonNull(formFields.getAttribute("class"))
                .contains("ng-invalid ng-invalid-required");
        Assertions.assertTrue(check, "Свойство класса локатора не совпадает с ожидаемым");
    }
}
