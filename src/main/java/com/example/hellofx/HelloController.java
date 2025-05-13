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
import java.util.Optional;

public class HelloController {
    @FXML
    private TextField vezetekNevInput;
    @FXML
    private TextField keresztNevInput;
    @FXML
    private DatePicker birthDateInput;
    @FXML
    private Spinner<Integer> schoolYearInput;
//    @FXML
//    private ComboBox<Student.SchoolClassType> osztalyInput;
    @FXML
    private ComboBox<String> tanulmanyiSzintInput;
    @FXML
    private TableView<Student> studentTableView;



    private ObservableList<Student> studentList;
    private ObjectProperty<Student> selectedStudent = new SimpleObjectProperty<Student>();
    @FXML
    public void initialize() {
        studentList = FXCollections.observableArrayList(
                new Student(1, "John", "Doe", LocalDate.of(2000, 1, 1), 12, Student.SchoolClassType.ClassA, "érettségi"),
                new Student(2, "Jane", "Smith", LocalDate.of(2001, 2, 2), 11, Student.SchoolClassType.ClassB, "szakmai érettségi"),
                new Student(3, "Michael", "Brown", LocalDate.of(1999, 3, 3), 10, Student.SchoolClassType.ClassC, "szakmai vizsga"),
                new Student(4, "Emily", "Davis", LocalDate.of(2002, 4, 4), 9, Student.SchoolClassType.ClassA, "érettségi"),
                new Student(5, "Chris", "Wilson", LocalDate.of(2003, 5, 5), 8, Student.SchoolClassType.ClassB, "szakmai érettségi"),
                new Student(6, "Sarah", "Taylor", LocalDate.of(2004, 6, 6), 7, Student.SchoolClassType.ClassC, "szakmai vizsga"),
                new Student(7, "David", "Anderson", LocalDate.of(2005, 7, 7), 6, Student.SchoolClassType.ClassA, "érettségi"),
                new Student(8, "Sophia", "Thomas", LocalDate.of(2006, 8, 8), 5, Student.SchoolClassType.ClassB, "szakmai érettségi"),
                new Student(9, "James", "Moore", LocalDate.of(2007, 9, 9), 4, Student.SchoolClassType.ClassC, "szakmai vizsga"),
                new Student(10, "Olivia", "Jackson", LocalDate.of(2008, 10, 10), 3, Student.SchoolClassType.ClassA, "érettségi")
        );

//        studentTableView.setItems(studentList);
        ObservableList<Student> unmodifiableStudentList = FXCollections.unmodifiableObservableList(studentList);

        // Set the unmodifiable list to the TableView
        studentTableView.setItems(unmodifiableStudentList);
        tanulmanyiSzintInput.setItems(FXCollections.observableArrayList("érettségi", "szakmai érettségi", "szakmai vizsga"));

        schoolYearInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));



        selectedStudent.addListener((obs, oldStudent, newStudent) -> {
            // Unbind previous bindings
            vezetekNevInput.textProperty().unbind();
            keresztNevInput.textProperty().unbind();
            birthDateInput.valueProperty().unbind();
            schoolYearInput.getValueFactory().valueProperty().unbind();
            tanulmanyiSzintInput.valueProperty().unbind();

            if (newStudent != null) {
                // Bind to the new selected student
                vezetekNevInput.textProperty().bindBidirectional(newStudent.firstNameProperty());
                keresztNevInput.textProperty().bindBidirectional(newStudent.lastNameProperty());
                birthDateInput.valueProperty().bindBidirectional(newStudent.birthDateProperty());
                schoolYearInput.getValueFactory().valueProperty().bindBidirectional(newStudent.schoolYearProperty().asObject());
                tanulmanyiSzintInput.valueProperty().bindBidirectional(newStudent.educationLevelProperty());
            } else {
                // Clear the input fields if no student is selected
                vezetekNevInput.clear();
                keresztNevInput.clear();
                birthDateInput.setValue(null);
                schoolYearInput.getValueFactory().setValue(1);
                tanulmanyiSzintInput.setValue(null);
            }
        });

        studentTableView.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            System.out.println("Factorying");
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {
                    System.out.println(studentTableView.getSelectionModel().getSelectedIndex());
                    System.out.println(studentList.get(studentTableView.getSelectionModel().getSelectedIndex()).getFirstName());
                    Student clickedStudent = row.getItem();
                    System.out.println("clickedStudent = " + clickedStudent.getFirstName());
                    if (selectedStudent.get() == clickedStudent) {
                        selectedStudent.set(null); // Clear the selection
                    } else {
                        selectedStudent.set(clickedStudent); // Update the ObjectProperty
                    }
                }
            });

            return  row;
        });
    }

    @FXML
    private void handleRowSelected(MouseEvent event) {
        Student clicked = studentTableView.getSelectionModel().getSelectedItem();

        if (clicked != null) {
            if (selectedStudent.get() == clicked) {
                selectedStudent.set(null); // Clear the selection
                return;
            }
            selectedStudent.set(clicked); // Update the ObjectProperty
        }
    }

    @FXML
    private void handleNewButtonClick(){
        Student newStudent = new Student();

        Optional<Student> studentWithMaxId = studentList.stream()
                .max((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));

        Optional<Integer> maxId = studentWithMaxId.map(Student::getId);

        newStudent.setId(maxId.orElse(0) + 1);

        selectedStudent = new SimpleObjectProperty<Student>();
    };
    @FXML
    private void handleSaveButtonClick() {
        System.out.println(selectedStudent);
        int id = selectedStudent.get().getId();

        studentList.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .ifPresent(student -> {
                    student.setFirstName(selectedStudent.get().getFirstName());
                    student.setLastName(selectedStudent.get().getLastName());
                    student.setBirthDate(selectedStudent.get().getBirthDate());
                    student.setSchoolYear(selectedStudent.get().getSchoolYear());
                    student.setSchoolClass(selectedStudent.get().getSchoolClass());
                    student.setEducationLevel(selectedStudent.get().getEducationLevel());
                });

        studentTableView.refresh();
    }
    @FXML
    private void handleDeleteButtonClick(){
        int id = selectedStudent.get().getId();
        studentList.removeIf(student -> student.getId() == id);
    };
}