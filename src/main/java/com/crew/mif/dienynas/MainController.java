package com.crew.mif.dienynas;

import SaveLoad.ExportFormat;
import SaveLoad.Exporter;
import SaveLoad.ExporterFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import studentData.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static helper.Utility.populateComboBox;
import static java.util.Collections.disjoint;
import static jdk.jfr.consumer.EventStream.openFile;

public class MainController {

    @FXML
    private ToggleGroup ExportOptions;

    @FXML
    private Button absentButton;

    @FXML
    private Button presentButton;

    @FXML
    private Button gradeButton;

    @FXML
    private ComboBox<Integer> gradeChoice;

    @FXML
    private ComboBox<String> studentChoice;

    @FXML
    private RadioButton csvChosen;

    @FXML
    private RadioButton pdfChosen;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker chosenDate;

    @FXML
    private ComboBox<String> filterChoice;

    @FXML
    private ComboBox<String> filteredChoice;

    @FXML
    private Button exportButton;

    @FXML
    private Button importButton;

    @FXML
    private TableColumn<Student, Boolean> studentAttendance;

    @FXML
    private TableColumn<Student, String> studentClass;

    @FXML
    private TableColumn<Student, Integer> studentGrade;

    @FXML
    private TableColumn<Student, String> studentGroup;

    @FXML
    private TableColumn<Student, String> studentID;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableView<Student> dataTable;

    @FXML
    private Button refreshButton;

    @FXML
    private TextField groupName;

    private Filter filter;

    private StudentTable studentTable;




    public void initialize() {
        studentTable = new StudentTable(dataTable, studentID, studentClass, studentName, studentGroup, studentGrade, studentAttendance);
        dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        addButton.setOnAction(this::handleAdd);
        exportButton.setOnAction(this::handleExport);
        importButton.setOnAction(this::handleImport);
        filterChoice.setOnAction(this::handleFilters);
        filteredChoice.setOnAction(this::handleFiltering);
        presentButton.setOnAction(this::handlePresent);
        absentButton.setOnAction(this::handleAbsent);
        studentChoice.setOnAction(this::handleStudentChoice);
        gradeChoice.setOnAction(this::handleGradeChoice);
        gradeButton.setOnAction(this::handleGrading);
        refreshButton.setOnAction(this::handleRefresh);
        groupName.setOnAction(this::handleGroupText);

        populateComboBox(10,gradeChoice);


    }

    private void handleRefresh(ActionEvent actionEvent) {
        if(studentTable.getDataTable() != null && studentTable.getTableData() != null){
            studentTable.toOriginal();
        }
    }

    private void handleGrading(ActionEvent actionEvent) {
        if(studentChoice.getSelectionModel().getSelectedItem() != null){
            studentTable.giveGrade(studentChoice.getSelectionModel().getSelectedItem(), gradeChoice.getSelectionModel().getSelectedItem());
        }
    }

    private void handleGradeChoice(ActionEvent actionEvent) {
        if(studentChoice.getSelectionModel().getSelectedItem() != null && gradeChoice.getSelectionModel().getSelectedItem() != null){
            gradeButton.setDisable(false);
            gradeButton.setVisible(true);
        }
    }

    private void handleStudentChoice(ActionEvent actionEvent) {
        if(studentChoice.getSelectionModel().getSelectedItem() != null){
            absentButton.setDisable(false);
            presentButton.setDisable(false);
            gradeChoice.setDisable(false);
        }
    }

    private void handleAbsent(ActionEvent actionEvent) {
        if(studentChoice.getSelectionModel().getSelectedItem() != null){
            studentTable.markStudentAsAbsent(studentChoice.getSelectionModel().getSelectedItem());
        }
    }

    private void handlePresent(ActionEvent actionEvent) {
        if(studentChoice.getSelectionModel().getSelectedItem() != null){
           studentTable.markStudentAsPresent(studentChoice.getSelectionModel().getSelectedItem());
        }
    }

    private void handleFiltering(ActionEvent actionEvent) {
        if(filteredChoice.getSelectionModel().getSelectedItem() != null)
            studentTable.filterTable(filter, filteredChoice.getSelectionModel().getSelectedItem());
    }

    private void handleFilters(ActionEvent actionEvent) {
        studentTable.toOriginal();
        if(filterChoice.getSelectionModel().getSelectedItem() != null){
            updateFilteredList();
            filteredChoice.setVisible(true);
            filteredChoice.setDisable(false);
        }
    }

    private void handleImport(ActionEvent actionEvent) {
        try {
            ObservableList<Student> students = FXCollections.observableArrayList();
            ObservableList<String> nameList = FXCollections.observableArrayList();
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV files", "*.csv"));

            File file = fileChooser.showOpenDialog(importButton.getScene().getWindow());
            if (file != null) {
                openFile(file.toPath());
            } else {
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String filters = reader.readLine();
            ObservableList<String> filterList = FXCollections.observableArrayList(Arrays.asList(filters.split(",")));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length != 6) {
                    System.err.println("Skipping invalid data format: " + Arrays.toString(values));
                    continue;
                }

                Student student = convertToStudent(values);
                if(student == null){
                    System.err.println("Skipping invalid data format: " + Arrays.toString(values));
                    continue;
                }
                nameList.add(student.getName());
                students.add(student);
            }
            studentTable.getDataTable().getItems().setAll(students);
            populateComboBox(studentChoice, nameList);
            populateComboBox(filterChoice, filterList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFilteredList() {
        ObservableList<Student> students = studentTable.getDataTable().getItems();
        ObservableList<String> filteredList = FXCollections.observableArrayList();
        Set<String> uniqueValues = new HashSet<>();

        String selectedColumn = filterChoice.getSelectionModel().getSelectedItem();

        for (Student student : students) {
            String value = null;
            //acts as a factory
            switch (selectedColumn) {
                case "Student ID":
                    filter = new IDFilter();
                    value = student.getId();
                    break;
                case "Class":
                    filter = new ClassFilter();
                    value = student.getCurrentClass();
                    break;
                case "Name":
                    filter = new NameFilter();
                    value = student.getName();
                    break;
                case "Group":
                    filter = new GroupFilter();
                    value = student.getGroup();
                    break;
                case "Grade":
                    filter = new GradeFilter();
                    value = String.valueOf(student.getGrade());
                    break;
                case "Attendance":
                    filter = new AttendanceFilter();
                    value = student.getAttendance() ? "true" : "false";
                    break;
                default:
                    System.err.println("Unknown column selected: " + selectedColumn);
                    return;
            }

            if (value != null) {
                uniqueValues.add(value);
            }
        }

        filteredList.setAll(uniqueValues);
        populateComboBox(filteredChoice, filteredList);
    }


    private Student convertToStudent(String[] values) {
        if (values != null && values.length == 6) {
            try {
                Student student = new Student();
                student.setId(values[0]);
                student.setCurrentClass(values[1]);
                student.setName(values[2]);
                student.setGroup(values[3]);
                student.setGrade(Integer.parseInt(values[4]));
                student.setAttendance(
                        Objects.equals(values[5], "1") ||
                        Objects.equals(values[5], "+") ||
                        Objects.equals(values[5], "yes") ||
                        Objects.equals(values[5], "true")
                );
                return student;
            } catch (NumberFormatException e) {
                System.err.println("Invalid grade format: " + values[4]);
            }
        } else {
            System.err.println("Invalid data format: " + Arrays.toString(values));
        }
        return null;
    }

    private void handleExport(ActionEvent actionEvent) {
        if(studentTable.getTableData() != null && !studentTable.getTableData().isEmpty()
        && (pdfChosen.isSelected() || csvChosen.isSelected())) {
            ExportFormat format = (pdfChosen.isSelected()) ? ExportFormat.PDF : ExportFormat.CSV;
            Exporter exporter = ExporterFactory.getExporter(format);
            try {
                exporter.exportData(studentTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAdd(ActionEvent actionEvent) {
        if(studentTable.getTableData() != null && !studentTable.getTableData().isEmpty()
        && !studentTable.getDataTable().getSelectionModel().getSelectedItems().isEmpty()
        && !groupName.getText().isEmpty()) {
            for (Student selectedStudent : studentTable.getDataTable().getSelectionModel().getSelectedItems()) {
                selectedStudent.setGroup(groupName.getText());
            }
        }
        studentTable.refresh();

    }

    private void handleGroupText(ActionEvent actionEvent){
        if(groupName.getText() != null && !groupName.getText().isEmpty())
            addButton.setDisable(false);
    }

}
