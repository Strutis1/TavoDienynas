package com.crew.mif.dienynas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableColumn<?, ?> studentAttendance;

    @FXML
    private ComboBox<?> studentChoice;

    @FXML
    private TableColumn<?, ?> studentGroup;

    @FXML
    private TableColumn<?, ?> studentName;

    @FXML
    private TextField studentSearch;

    @FXML
    private TableView<?> studentTable;

}
