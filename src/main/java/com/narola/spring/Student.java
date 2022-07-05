package com.narola.spring;

import javax.persistence.*;

@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "uni_id", referencedColumnName = "subId")
    private Univercity univercity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Univercity getUnivercity() {
        return univercity;
    }

    public void setUnivercity(Univercity univercity) {
        this.univercity = univercity;
    }
}
