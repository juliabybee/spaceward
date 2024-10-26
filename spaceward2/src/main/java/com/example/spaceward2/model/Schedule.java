package com.example.spaceward2.model;

import javax.persistence.*;

@Entity
@Table(name = "schedules") // Specifies the table name in the database
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key with auto-increment
    @Column(name = "id") // Explicitly defines the column name for the ID field
    private int id;

    @Column(name = "grade") // Maps the grade field to the "grade" column
    private float grade;

    @Column(name = "teacher_name") // Maps the teacherName field to the "teacher_name" column
    private String teacherName;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
