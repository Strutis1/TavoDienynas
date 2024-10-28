package SaveLoad;

import javafx.event.ActionEvent;
import studentData.Student;
import studentData.StudentTable;

import java.io.*;
import java.util.List;

public class CSVExporter extends Exporter{
    public CSVExporter() {
        super();
    }

    @Override
    public void exportData(StudentTable studentTable) throws IOException {
        if(studentTable.getTableData() != null && !studentTable.getTableData().isEmpty()) {
            File file = new File("Student_Data.csv");
            try (Writer writer = new BufferedWriter(new FileWriter(file))) {
                String columnNames = "Student ID, Class, Name, Group, Grade, Attendance\n";
                writer.write(columnNames);
                for (Student student : studentTable.getDataTable().getItems()) {
                    String text = student.getId() + "," + student.getCurrentClass() + "," +
                            student.getName() + "," + student.getGroup() + "," + student.getGrade() + "," + student.getAttendance() + "\n";
                    writer.write(text);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
