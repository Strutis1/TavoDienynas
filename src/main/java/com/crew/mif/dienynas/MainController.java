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
import static jdk.jfr.consumer.EventStream.openFile;

public class MainController {

    @FXML
    private ToggleGroup ExportOptions;

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
    private TableColumn<Student, Double> studentGrade;

    @FXML
    private TableColumn<Student, String> studentGroup;

    @FXML
    private TableColumn<Student, String> studentID;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableView<Student> dataTable;

    private Filter filter;

    private StudentTable studentTable;


    public void initialize() {
        studentTable = new StudentTable(dataTable, studentID, studentClass, studentName, studentGroup, studentGrade, studentAttendance);
        addButton.setOnAction(this::handleAdd);
        exportButton.setOnAction(this::handleExport);
        importButton.setOnAction(this::handleImport);
        filterChoice.setOnAction(this::handleFilters);
        filteredChoice.setOnAction(this::handleFiltering);


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
                students.add(student);
            }
            studentTable.getDataTable().getItems().setAll(students);
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
                    value = student.getAttendance() ? "Yes" : "No";
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
                student.setGrade(Double.parseDouble(values[4]));
                student.setAttendance(
                        Objects.equals(values[5], "1") ||
                        Objects.equals(values[5], "+") ||
                        Objects.equals(values[5], "yes")
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

    }

}
