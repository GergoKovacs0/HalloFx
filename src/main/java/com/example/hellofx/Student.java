package com.example.hellofx;

import java.time.LocalDate;
import java.util.Date;



public class Student {

    public enum SchoolClassType { ClassA, ClassB, ClassC}

    private int Id;
    private String FirstName;
    private String LastName;
    private LocalDate BirthDate;
    private int SchoolYear;
    private SchoolClassType SchoolClass;
    private String EducationLevel;

    public Student(int id, String firstName, String lastName, LocalDate birthDate, int schoolYear, SchoolClassType schoolClass, String educationLevel) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        BirthDate = birthDate;
        SchoolYear = schoolYear;
        SchoolClass = schoolClass;
        EducationLevel = educationLevel;
    }
    
    public Student(){
        Id = 0;
        FirstName = "";
        LastName = "";
        BirthDate = LocalDate.now();
        SchoolYear = 0;
        SchoolClass = SchoolClassType.ClassA;
        EducationLevel = "";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public LocalDate getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        BirthDate = birthDate;
    }

    public int getSchoolYear() {
        return SchoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        SchoolYear = schoolYear;
    }

    public SchoolClassType getSchoolClass() {
        return SchoolClass;
    }

    public void setSchoolClass(SchoolClassType schoolClass) {
        SchoolClass = schoolClass;
    }

    public String getEducationLevel() {
        return EducationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        EducationLevel = educationLevel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", BirthDate=" + BirthDate +
                ", SchoolYear=" + SchoolYear +
                ", SchoolClass=" + SchoolClass +
                ", EducationLevel='" + EducationLevel + '\'' +
                '}';
    }
}
