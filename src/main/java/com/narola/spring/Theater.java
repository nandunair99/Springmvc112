package com.narola.spring;

import javax.persistence.*;

@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tId;

    @Column(name = "tName", nullable = false)
    private String tName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "addressLine1",
                    column = @Column(name = "theater_add_line1")),
            @AttributeOverride(name = "addressLine2",
                    column = @Column(name = "theater_add_line2"))
    })
    private Address address;

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
