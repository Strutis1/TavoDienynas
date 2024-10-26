package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentTable {
    private ObservableList<Student> tableData = FXCollections.observableArrayList();
    private final TableView<Student> dataTable;

    public StudentTable(TableView<Student> dataTable,
                           TableColumn<Student, String> studentID,
                           TableColumn<Student, String> currentClass,
                           TableColumn<Student, String> studentName,
                           TableColumn<Student, String> studentGroup,
                           TableColumn<Student, Double> studentGrade,
                           TableColumn<Student, Boolean> studentAttendance) {
        this.dataTable = dataTable;

        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        currentClass.setCellValueFactory(new PropertyValueFactory<>("currentClass"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentGroup.setCellValueFactory(new PropertyValueFactory<>("studentGroup"));
        studentGrade.setCellValueFactory(new PropertyValueFactory<>("studentGrade"));
        studentAttendance.setCellValueFactory(new PropertyValueFactory<>("studentAttendance"));

        dataTable.setItems(tableData);
    }

    public StudentTable(TableView<Student> dataTable, TableColumn<Student, String> studentID, TableColumn<Student, String> studentClass, TableColumn<Student, String> studentName, TableColumn<Student, Double> studentGrade, TableColumn<Student, Boolean> studentAttendance) {
        this.dataTable = dataTable;
    }

//    public void filterByMonthRange(int fromMonth, int toMonth) {
//        ObservableList<Mokejimas> filteredList = FXCollections.observableArrayList();
//
//        for (Mokejimas mokejimas : tableData) {
//            if (mokejimas.getMenuo() >= fromMonth && mokejimas.getMenuo() <= toMonth) {
//                filteredList.add(mokejimas);
//            }
//        }
//        dataTable.setItems(filteredList);
//        dataTable.refresh();
//
//    }

    public void clearTable() {
        tableData.clear();
        dataTable.refresh();
    }

    public ObservableList<Student> getTableData() {
        return tableData;
    }

    private void setTableData(ObservableList<Student> tableData) {
        this.tableData = tableData;
    }

    public void setVisible(boolean visible){
        dataTable.setVisible(visible);
    }

    public void addPayment(Student mokejimas) {
        tableData.add(mokejimas);
        dataTable.refresh();
    }
}
