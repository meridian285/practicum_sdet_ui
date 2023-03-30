package com.globalsqa.angularJs_protractor.bankingproject.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CustomersPage class
 */

public class CustomersPage extends ManagePage {
    //Локатор поля поиска
    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    private WebElement searchField;
    //Локатор для списка клиентов
    @FindBy(xpath = "//td[@class='ng-binding']")
    private List<WebElement> listCustomers;
    //Локатор заголовка столбца First Name
    @FindBy(xpath = "//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    private WebElement titleFirstName;

    public CustomersPage(){
        PageFactory.initElements(driver, this);
    }

    @Step("Поиск клиента по тексту")
    public CustomersPage findText(String text){
        searchField.sendKeys(text);
        return this;
    }

    @Step("Клик по заголовку столбца First Name, для сортировки")
    public CustomersPage clickOnTitleFirstName(){
        titleFirstName.click();
        return this;
    }

    @Step("Проверка что клиент найден")
    public void checkFindCustomers(String firstName, String lastName, String postCode){
        //цикл перебирает все полученные подсписки и сверяет с ожидаемым результатом
        for (int i = 0; i < getListCustomers().size(); i++) {
            List<String> expected = Arrays.asList(firstName,lastName,postCode); //Данные полученные из генератора данных
                Assertions.assertEquals(expected, getListCustomers().get(i),
                        "Ожидалось совпадение поиска клиента");
        }
    }

    @Step("Проверка сортировки таблицы по первому столбцу First Name")
    public void checkSortingTable(){
        List<String> actual = new ArrayList<>();
        for (List<String> strings : getListCustomers()) {
            actual.add(strings.get(0));
        }
        List<String> expected = actual.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        Assertions.assertEquals(expected, actual, "Ожидалось совпадение списков");
    }

    //Метод получения списков всех клиентов
    private List<List<String>> getListCustomers(){
        //переводим список веб элементов в список строку
        List<String> list = new ArrayList<>();
        listCustomers.forEach(variable -> list.add(variable.getText()));
        //Разбиваем список на подсписки
        List<List<String>> sublist = new ArrayList<>();
        for (int i = 0; i < list.size(); i += 3) {
            sublist.add(list.subList(i, Math.min(i + 3, listCustomers.size())));
        }
        return sublist;
    }
}
