package com.project.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String className;
    @Column
    private String streetAdress;
    @Column
    private Integer houseNumber;
    @Column
    private String city;
    @Column
    private String zipCode;
    @Column
    private Integer pesel;
    @Column
    private Date dateOfBirth;
    @Column
    private Boolean dyslexia;
    @OneToMany
    @JoinTable(name = "grade", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Grade> studentGrade;

    public Student() {

    }

    public Student(Integer id, String firstName, String lastName, String class2, String streetAdress, Integer houseNumber, String city, String zipCode, Integer pesel, Date dateOfBirth, Boolean dyslexia) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = class2;
        this.streetAdress = streetAdress;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
        this.dyslexia = dyslexia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClass2() {
        return className;
    }

    public void setClass2(String class2) {
        this.className = class2;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getPesel() {
        return pesel;
    }

    public void setPesel(Integer pesel) {
        this.pesel = pesel;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getDyslexia() {
        return dyslexia;
    }

    public void setDyslexia(Boolean dyslexia) {
        this.dyslexia = dyslexia;
    }

    public List<Grade> getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(List<Grade> studentGrade) {
        this.studentGrade = studentGrade;
    }
}
