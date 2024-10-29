package studentData;

public class Student {
    private String id;
    private int grade;
    private String currentClass;
    private String name;
    private String group;
    private boolean attendance;

    public Student(){}

    public Student(String id, int grade, String currentClass, String name, String group, boolean attendance) {
        this.id = id;
        this.grade = grade;
        this.currentClass = currentClass;
        this.name = name;
        this.group = group;
        this.attendance = attendance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        if(grade >= 0 && grade <= 10)
            this.grade = grade;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
