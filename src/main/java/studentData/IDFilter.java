package studentData;

public class IDFilter extends Filter{
    @Override
    public String getColumnData(Student student) {
        return student.getId();
    }
}
