package com.globalsqa.angularJs_protractor.bankingproject.tests;

import com.globalsqa.angularJs_protractor.bankingproject.config.BaseTest;
import com.globalsqa.angularJs_protractor.bankingproject.generator.Customer;
import com.globalsqa.angularJs_protractor.bankingproject.generator.DataGenerator;
import com.globalsqa.angularJs_protractor.bankingproject.pages.AddCustomerPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.MainPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.ManagePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Позитивный тест на создание клиента")
    public void createCustomerValidCredentials(){
        mainPage.clickBankManagerLoginButton();
        managePage.clickAddCustomerButton()
                .createCustomer(customer.getFirstName(), customer.getLastName(), customer.getPostCode())
                .checkMessageCreateCustomer();
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

}
