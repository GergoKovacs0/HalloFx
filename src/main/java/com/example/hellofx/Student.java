package com.example.hellofx;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;



public class Student {

    public enum SchoolClassType { ClassA, ClassB, ClassC }

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>();
    private final IntegerProperty schoolYear = new SimpleIntegerProperty();
    private final ObjectProperty<SchoolClassType> schoolClass = new SimpleObjectProperty<>();
    private final StringProperty educationLevel = new SimpleStringProperty();

    public Student(int id, String firstName, String lastName, LocalDate birthDate, int schoolYear, SchoolClassType schoolClass, String educationLevel) {
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.birthDate.set(birthDate);
        this.schoolYear.set(schoolYear);
        this.schoolClass.set(schoolClass);
        this.educationLevel.set(educationLevel);
    }

    public Student() {
        this(0, "", "", LocalDate.now(), 0, SchoolClassType.ClassA, "");
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public int getSchoolYear() {
        return schoolYear.get();
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear.set(schoolYear);
    }

    public IntegerProperty schoolYearProperty() {
        return schoolYear;
    }

    public SchoolClassType getSchoolClass() {
        return schoolClass.get();
    }

    public void setSchoolClass(SchoolClassType schoolClass) {
        this.schoolClass.set(schoolClass);
    }

    public ObjectProperty<SchoolClassType> schoolClassProperty() {
        return schoolClass;
    }

    public String getEducationLevel() {
        return educationLevel.get();
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel.set(educationLevel);
    }

    public StringProperty educationLevelProperty() {
        return educationLevel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", BirthDate=" + birthDate +
                ", SchoolYear=" + schoolYear +
                ", SchoolClass=" + schoolClass +
                ", EducationLevel='" + educationLevel + '\'' +
                '}';
    }
}
