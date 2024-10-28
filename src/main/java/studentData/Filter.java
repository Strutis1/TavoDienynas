package studentData;

import studentData.Student;

public abstract class Filter {
    public abstract String getColumnData(Student student);

    public boolean match(Student student, String filterValue) {
        return getColumnData(student).equals(filterValue);
    }
}
