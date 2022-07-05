package com.narola.spring;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_Univercity")
public class Univercity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;

    private String subject;

    @OneToMany(mappedBy = "univercity")
    private List<Student> studentList;


    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
