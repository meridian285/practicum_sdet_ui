package com.globalsqa.angularJs_protractor.bankingproject.tests;

import com.globalsqa.angularJs_protractor.bankingproject.config.BaseTest;
import com.globalsqa.angularJs_protractor.bankingproject.generator.Customer;
import com.globalsqa.angularJs_protractor.bankingproject.generator.DataGenerator;
import com.globalsqa.angularJs_protractor.bankingproject.pages.AddCustomerPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.CustomersPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.MainPage;
import com.globalsqa.angularJs_protractor.bankingproject.pages.ManagePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CustomersTests тестовый класс, для тестов связанных со страницей Customers
 */

public class CustomersTests extends BaseTest {
    Customer customer = DataGenerator.getCustomerFaker();
    CustomersPage customersPage = new CustomersPage();
    AddCustomerPage addCustomerPage = new AddCustomerPage();
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
                .createCustomer(firstName, lastName, postCode);
        assertTrue(managePage.getTextAlertMessage().contains("Customer added successfully with customer id :"),
                "Сообщение подтверждения создания клиента не корректно или отсутствует");
        managePage.clickAlertAccept();
        addCustomerPage.clickCustomersButton()
                .findText(firstName)
                .checkFindCustomers(firstName, lastName, postCode);
    }

    @Test
    @DisplayName("Сортировка таблицы по имени (First Name)")
    public void checkSortingTable(){
        mainPage.clickBankManagerLoginButton();
        managePage
                .clickCustomersButton()
                .clickOnTitleFirstName();
        //listSortedBySite() список сортированный сайтом
        //sortingListInDescending() тот же список сортированный в методе
        Assertions.assertEquals(customersPage.sortingListInDescending(), customersPage.listSortedBySite(),
                "Ожидалось совпадение списков");
    }
}
