package com.narola.spring;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {

    @EmbeddedId
    private CustomerPK customerPK;

    private String registrationNumber;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public CustomerPK getCustomerPK() {
        return customerPK;
    }

    public void setCustomerPK(CustomerPK customerPK) {
        this.customerPK = customerPK;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerPK=" + customerPK +
                ", registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
}
