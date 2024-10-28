package studentData;

public class ClassFilter extends Filter{
    @Override
    public String getColumnData(Student student) {
        return student.getCurrentClass();
    }
}
