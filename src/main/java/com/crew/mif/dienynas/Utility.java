package com.crew.mif.dienynas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Utility {

    public static boolean isPosInt(TextField textField) {
        try {
            int value;
            if (textField.getText() == null || textField.getText().trim().isEmpty()) {
                textField.setStyle("-fx-border-color: red");
                return false;
            } else {
                value = Integer.parseInt(textField.getText());
            }

            if (value >= 0) {
                textField.setStyle("-fx-border-color: black");
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
    }

    public static boolean isPosDouble(TextField textField) {
        try {
            double value;
            if (textField.getText() == null || textField.getText().trim().isEmpty()) {
                textField.setStyle("-fx-border-color: red");
                return false;
            } else {
                value = Double.parseDouble(textField.getText());
            }
            if (value >= 0) {
                textField.setStyle("-fx-border-color: black");
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            textField.setStyle("-fx-border-color: red");
            return false;
        }
    }

    public static void populateComboBox(int count, ComboBox<Integer> comboBox) {
        comboBox.getItems().clear();
        ObservableList<Integer> items = FXCollections.observableArrayList();
        for (int i = 1; i <= count; ++i) {
            items.add(i);
        }
        comboBox.setItems(items);
    }


    public static void populateComboBox(ComboBox<String> comboBox, ObservableList<String> items) {
        comboBox.getItems().clear();
        comboBox.setItems(items);
    }
}
