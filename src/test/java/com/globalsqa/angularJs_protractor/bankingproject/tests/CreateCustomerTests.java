package com.globalsqa.angularJs_protractor.bankingproject.tests;

import com.globalsqa.angularJs_protractor.bankingproject.config.BaseTest;
import com.globalsqa.angularJs_protractor.bankingproject.generator.Customer;
import com.globalsqa.angularJs_protractor.bankingproject.generator.DataGenerator;
import com.globalsqa.angularJs_protractor.bankingproject.pages.AddCustomerPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.MainPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.ManagePage;import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.html5.WebStorage;

/**
 * Класс для тестов создания клиентов
 */

public class CreateCustomerTests extends BaseTest {

    Customer customer = DataGenerator.getCustomerFaker();
    MainPage mainPage = new MainPage();
    ManagePage managePage = new ManagePage() {
        @Override
        public AddCustomerPage clickAddCustomerButton() {
            return super.clickAddCustomerButton();
        }
    };

    @AfterEach
    public void clearCash(){
        ((WebStorage) driver).getLocalStorage().clear();
        ((WebStorage) driver).getSessionStorage().clear();
    }

    @Test
    @DisplayName("Позитивный тест на создание клиента")
    public void createCustomerValidCredentials(){
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(customer.getFirstName(), customer.getLastName(), customer.getPostCode())
                .checkMessageCreateCustomer();
    }

    @Test
    @DisplayName("Тест на повторное создание существующего клиента")
    public void createIdenticalCustomer(){
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String postCode = customer.getPostCode();
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(firstName, lastName, postCode)
                .checkMessageCreateCustomer()
                .createCustomer(firstName, lastName, postCode)
                .checkMessageCreateDuplicateCustomer();
    }

    @Test
    @DisplayName("Тест на создание клиента без поля First Name")
    public void createCustomerWithoutFirstName() {
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer("", customer.getLastName(), customer.getPostCode())
                .checkingThatFieldsNotFilled();
    }

    @Test
    @DisplayName("Тест на создание клиента без поля Last Name")
    public void createCustomerWithoutLastName() {
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(customer.getFirstName(), "", customer.getPostCode())
                .checkingThatFieldsNotFilled();
    }

    @Test
    @DisplayName("Тест на создание клиента без поля Post Code")
    public void createCustomerWithoutPostCode(){
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(customer.getFirstName(), customer.getLastName(), "")
                .checkingThatFieldsNotFilled();
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест на создание клиента")
    @CsvFileSource(resources = "/test-data.csv", delimiter = '|', numLinesToSkip = 1)
    public void createCustomerParamTest(String firstName, String lastName, String postCode, boolean check) {
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(firstName, lastName, postCode)
                .checkMessageCreateCustomerParam(check);
    }
}
