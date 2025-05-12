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
                new Student(2, "Jane", "Smith", LocalDate.of(2001, 2, 2), 11, Student.SchoolClassType.ClassB, "szakmai érettségi")
        );

        studentTableView.setItems(studentList);

        tanulmanyiSzintInput.setItems(FXCollections.observableArrayList("érettségi", "szakmai érettségi", "szakmai vizsga"));

        schoolYearInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 1));



        selectedStudent.addListener((obs, oldStudent, newStudent) -> {
            if (newStudent != null) {
                vezetekNevInput.textProperty().bindBidirectional(newStudent.firstNameProperty());
                keresztNevInput.textProperty().bindBidirectional(newStudent.lastNameProperty());
                birthDateInput.valueProperty().bindBidirectional(newStudent.birthDateProperty());
                schoolYearInput.getValueFactory().valueProperty().bindBidirectional(newStudent.schoolYearProperty().asObject());
                tanulmanyiSzintInput.valueProperty().bindBidirectional(newStudent.educationLevelProperty());
            }
        });

        studentTableView.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                Student clickedStudent = row.getItem();
                studentTableView.getSelectionModel().select(clickedStudent);
            });

            return  row;
        });
    }

    @FXML
    private void handleRowSelected(MouseEvent event) {
        System.out.println(studentList.getFirst().getFirstName());
        Student clicked = studentList.get(studentTableView.getSelectionModel().getFocusedIndex());
        System.out.println(studentTableView.getSelectionModel().getFocusedIndex());
        System.out.println("clicked = " + clicked);

        if (clicked != null) {
            if (selectedStudent.get() == clicked) {
                selectedStudent.set(null); // Clear the selection
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