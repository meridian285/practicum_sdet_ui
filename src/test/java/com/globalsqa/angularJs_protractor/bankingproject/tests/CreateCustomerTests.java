package com.globalsqa.angularJs_protractor.bankingproject.tests;

import com.globalsqa.angularJs_protractor.bankingproject.config.BaseTest;
import com.globalsqa.angularJs_protractor.bankingproject.generator.Customer;
import com.globalsqa.angularJs_protractor.bankingproject.generator.DataGenerator;
import com.globalsqa.angularJs_protractor.bankingproject.pages.AddCustomerPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.MainPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.ManagePage;import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.html5.WebStorage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс для тестов создания клиентов
 */

public class CreateCustomerTests extends BaseTest {

    Customer customer = DataGenerator.getCustomerFaker();
    AddCustomerPage addCustomerPage = new AddCustomerPage();
    MainPage mainPage = new MainPage();
    ManagePage managePage = new ManagePage() {
        @Override
        public AddCustomerPage clickAddCustomerButton() {
            return super.clickAddCustomerButton();
        }
    };

    @AfterEach
    public void clearCash() {
        ((WebStorage) driver).getLocalStorage().clear();
        ((WebStorage) driver).getSessionStorage().clear();
    }

    @Test
    @DisplayName("Позитивный тест на создание клиента")
    public void createCustomerValidCredentials(){
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(customer.getFirstName(), customer.getLastName(), customer.getPostCode());
        assertTrue(managePage.getTextAlertMessage().contains("Customer added successfully with customer id :"),
                "Сообщение подтверждения создания клиента не корректно или отсутствует");
        managePage.clickAlertAccept();
    }

    @Test
    @DisplayName("Тест на повторное создание существующего клиента")
    public void createDuplicateCustomer(){
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String postCode = customer.getPostCode();
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(firstName, lastName, postCode);
        assertTrue(managePage.getTextAlertMessage().contains("Customer added successfully with customer id :"),
                "Сообщение подтверждения создания клиента не корректно или отсутствует");
        addCustomerPage.clickAlertAccept();
        addCustomerPage.createCustomer(firstName, lastName, postCode);
        Assertions.assertFalse(managePage.getTextAlertMessage().contains("Customer added successfully with customer id :"),
                "Сообщение подтверждения создания клиента не корректно или отсутствует");
        managePage.clickAlertAccept();
    }

    @Test
    @DisplayName("Тест на создание клиента без поля First Name")
    public void createCustomerWithoutFirstName() {
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer("", customer.getLastName(), customer.getPostCode());
        Assertions.assertTrue(addCustomerPage.checkingThatFieldsNotFilled(),
                "Свойство класса локатора не совпадает с ожидаемым");
    }

    @Test
    @DisplayName("Тест на создание клиента без поля Last Name")
    public void createCustomerWithoutLastName() {
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(customer.getFirstName(), "", customer.getPostCode());
        Assertions.assertTrue(addCustomerPage.checkingThatFieldsNotFilled(),
                "Свойство класса локатора не совпадает с ожидаемым");
    }

    @Test
    @DisplayName("Тест на создание клиента без поля Post Code")
    public void createCustomerWithoutPostCode(){
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(customer.getFirstName(), customer.getLastName(), "");
        Assertions.assertTrue(addCustomerPage.checkingThatFieldsNotFilled(),
                "Свойство класса локатора не совпадает с ожидаемым");
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест на создание клиента")
    @CsvFileSource(resources = "/test-data.csv", delimiter = '|', numLinesToSkip = 1)
    public void createCustomerParamTest(String firstName, String lastName, String postCode, boolean check) throws InterruptedException {
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(firstName, lastName, postCode);
        Assertions.assertEquals(check,addCustomerPage.checkMessageCreateCustomerParam(),
                        "Сообщение подтверждения создания клиента не корректно или отсутствует");
        addCustomerPage.clickAlertAccept();
        managePage.clickCustomersButton().clickDeleteButton();
    }
}
