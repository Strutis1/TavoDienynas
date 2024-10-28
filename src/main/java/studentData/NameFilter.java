package studentData;

public class NameFilter extends Filter{
    @Override
    public String getColumnData(Student student) {
        return student.getName();
    }
}
