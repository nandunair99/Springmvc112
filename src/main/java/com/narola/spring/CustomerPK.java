package com.narola.spring;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CustomerPK implements Serializable {

    private String custMob;
    private String custEmail;

    public CustomerPK() {
    }

    public CustomerPK(String custMob, String custEmail) {
        this.custMob = custMob;
        this.custEmail = custEmail;
    }

    public String getCustMob() {
        return custMob;
    }

    public void setCustMob(String custMob) {
        this.custMob = custMob;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    @Override
    public String toString() {
        return "CustomerPK{" +
                "custMob='" + custMob + '\'' +
                ", custEmail='" + custEmail + '\'' +
                '}';
    }
}
