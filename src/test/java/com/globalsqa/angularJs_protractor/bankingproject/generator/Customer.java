package com.globalsqa.angularJs_protractor.bankingproject.generator;

import io.qameta.allure.Step;
import net.datafaker.Faker;

public class Customer {
    private String firstName;
    private String lastName;
    private String postCode;

    public Customer(String firstname, String lastName) {
        this.firstName = firstname;
        this.lastName = lastName;
    }

    public Customer(String firstname, String lastName, String postCode) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.postCode = postCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
