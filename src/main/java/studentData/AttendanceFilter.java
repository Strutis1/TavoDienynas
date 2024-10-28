package studentData;

public class AttendanceFilter extends Filter{
    @Override
    public String getColumnData(Student student) {
        return Boolean.toString(student.getAttendance());
    }
}
