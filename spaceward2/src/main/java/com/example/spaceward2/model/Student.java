package com.example.spaceward2.model;



import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studentNo", columnDefinition = "BINARY(16)")
    private UUID studentNo;

    public UUID getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(UUID studentNo) {
        this.studentNo = studentNo;
    }

    @Column(name = "firstName")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "gpaValue")
    private float gpaValue;

    public float getGpaValue() {
        return gpaValue;
    }

    public void setGpaValue(float gpaValue) {
        this.gpaValue = gpaValue;
    }
    

}