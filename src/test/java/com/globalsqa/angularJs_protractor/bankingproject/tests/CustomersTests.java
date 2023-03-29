package com.globalsqa.angularJs_protractor.bankingproject.tests;

import com.globalsqa.angularJs_protractor.bankingproject.config.BaseTest;
import com.globalsqa.angularJs_protractor.bankingproject.generator.Customer;
import com.globalsqa.angularJs_protractor.bankingproject.generator.DataGenerator;
import com.globalsqa.angularJs_protractor.bankingproject.pages.CustomersPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.MainPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.ManagePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * CustomersTests тестовый класс, для тестов связанных со страницей Customers
 */

public class CustomersTests extends BaseTest {
    Customer customer = DataGenerator.getCustomerFaker();
    MainPage mainPage = new MainPage();
    ManagePage managePage = new ManagePage() {
        @Override
        public CustomersPage clickCustomersButton() {
            return super.clickCustomersButton();
        }
    };

    @Test
    @DisplayName("Тест на поиск по First Name")
    public void checkSearchCustomer(){
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String postCode = customer.getPostCode();
        mainPage.clickBankManagerLoginButton();
        managePage
                .clickAddCustomerButton()
                .createCustomer(firstName, lastName, postCode)
                .checkMessageCreateCustomer()
                .clickCustomersButton()
                .findText(firstName)
                .checkFindCustomers(firstName, lastName, postCode);
    }

    @Test
    @DisplayName("Сортировка таблицы по имени (First Name)")
    public void checkSortingTable(){
        mainPage.clickBankManagerLoginButton();
        managePage
                .clickCustomersButton()
                .clickOnTitleFirstName()
                .checkSortingTable();
    }
}
