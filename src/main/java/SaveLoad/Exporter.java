package SaveLoad;

import studentData.Student;
import studentData.StudentTable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Exporter {
    public abstract void exportData(StudentTable studentTable) throws IOException;
}

