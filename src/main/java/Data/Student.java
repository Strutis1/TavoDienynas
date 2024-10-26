package Data;

public class Student {
    private int id;
    private float grade;
    private String currentClass;
    private String name;
    private Group group;
    private boolean attendance;


    public Student(int id, float grade, String currentClass, String name, Group group, boolean attendance) {
        this.id = id;
        this.grade = grade;
        this.currentClass = currentClass;
        this.name = name;
        this.group = group;
        this.attendance = attendance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
