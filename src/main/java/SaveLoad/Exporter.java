package SaveLoad;

import studentData.StudentTable;

import java.io.IOException;

public abstract class Exporter {
    public abstract void exportData(StudentTable studentTable) throws IOException;
}

