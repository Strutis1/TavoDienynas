package studentData;

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

        studentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        currentClass.setCellValueFactory(new PropertyValueFactory<>("currentClass"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        studentGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        studentAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        dataTable.setItems(tableData);
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

    public void filterTable(Filter filter, String wantedValue) {
        ObservableList<Student> filteredList = FXCollections.observableArrayList();
        for (Student student : tableData){
            if(filter.match(student,wantedValue)){
                filteredList.add(student);
            }
        }
        dataTable.setItems(filteredList);
    }

    public void toOriginal(){
        dataTable.setItems(tableData);
    }

    public TableView<Student> getDataTable() {
        return dataTable;
    }

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

    public void addStudent(Student student) {
        tableData.add(student);
        dataTable.refresh();
    }
}
