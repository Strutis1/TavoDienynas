package studentData;

public class GroupFilter extends Filter{
    @Override
    public String getColumnData(Student student) {
        return student.getGroup();
    }
}
