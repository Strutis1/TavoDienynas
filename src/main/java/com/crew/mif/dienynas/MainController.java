package com.crew.mif.dienynas;

import Data.Student;
import Data.StudentTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

import static com.crew.mif.dienynas.Utility.*;
import static jdk.jfr.consumer.EventStream.openFile;

public class MainController {

    @FXML
    private Button addButton;

    @FXML
    private DatePicker chosenDate;

    @FXML
    private Button exportButton;

    @FXML
    private Button importButton;

    @FXML
    private TextField searchText;

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

    private StudentTable studentTable;

    //TODO populate combo box with students

    public void initialize(){
        studentTable = new StudentTable(dataTable,studentID,studentClass,studentName, studentGrade, studentAttendance);
        addButton.setOnAction(this::handleAdd);
        exportButton.setOnAction(this::handleExport);
        importButton.setOnAction(this::handleImport);
        searchText.setOnAction(this::handleSearch);
    }

    private void handleSearch(ActionEvent actionEvent) {

    }

    private void handleImport(ActionEvent actionEvent) {
        try {
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(importButton.getScene().getWindow());
            if (file != null) {
                openFile(file.toPath());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleExport(ActionEvent actionEvent) {

    }

    private void handleAdd(ActionEvent actionEvent) {

    }

}
