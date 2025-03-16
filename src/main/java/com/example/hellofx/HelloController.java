package com.example.hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class HelloController {
    @FXML
    private TextField vezetekNevInput;
    @FXML
    private TextField keresztNevInput;
    @FXML
    private DatePicker birthDateInput;
    @FXML
    private Spinner<Integer> schoolYearInput;
    @FXML
    private ComboBox<Student.SchoolClassType> osztalyInput;
    @FXML
    private ComboBox<String> tanulmanyiSzintInput;
    @FXML
    private TableView<Student> studentTableView;

    private ObservableList<Student> studentList;
    private ObjectProperty<Student> selectedStudent = new SimpleObjectProperty<Student>(new Student());
    @FXML
    public void initialize() {
        System.out.println("HelloController initialized");
        studentList = FXCollections.observableArrayList(
                new Student("John", "Doe", LocalDate.of(2000, 1, 1), 12, Student.SchoolClassType.ClassA, "érettségi"),
                new Student("Jane", "Smith", LocalDate.of(2001, 2, 2), 11, Student.SchoolClassType.ClassB, "szakmai érettségi")
        );

        studentTableView.setItems(studentList);

        tanulmanyiSzintInput.setItems(FXCollections.observableArrayList("érettségi", "szakmai érettségi", "szakmai vizsga"));

        studentTableView.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Student clickedStudent = row.getItem();
                    selectedStudent = new SimpleObjectProperty<>(clickedStudent);
                }
            });
            return row;
        });

        selectedStudent.addListener((obs, oldStudent, newStudent) -> {
            if (newStudent != null) {
                vezetekNevInput.setText(newStudent.getLastName());
                keresztNevInput.setText(newStudent.getFirstName());
                birthDateInput.setValue(newStudent.getBirthDate());
                schoolYearInput.getValueFactory().setValue(newStudent.getSchoolYear());
                osztalyInput.setValue(newStudent.getSchoolClass());
                tanulmanyiSzintInput.setValue(newStudent.getEducationLevel());
            } else {
                vezetekNevInput.clear();
                keresztNevInput.clear();
                birthDateInput.setValue(null);
                schoolYearInput.getValueFactory().setValue(0);
                osztalyInput.setValue(null);
                tanulmanyiSzintInput.setValue(null);
            }
        });
    }


}