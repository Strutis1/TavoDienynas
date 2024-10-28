package studentData;

public class GradeFilter extends Filter {

    @Override
    public String getColumnData(Student student) {
        return Double.toString(student.getGrade());
    }
}
