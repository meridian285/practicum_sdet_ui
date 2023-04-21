package com.globalsqa.angularJs_protractor.bankingproject.generator;

import io.qameta.allure.Step;
import net.datafaker.Faker;

public class DataGenerator {
    Faker faker = new Faker();

    @Step("Случайные данные для Customer - firstName, lastName, postCode")
    public static Customer getCustomerFaker() {
        Faker faker = new Faker();
        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();
        var postCode = faker.address().buildingNumber();
        return new Customer(firstName, lastName, postCode);
    }
}
