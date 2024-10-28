package SaveLoad;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import studentData.Student;
import studentData.StudentTable;

import java.io.IOException;

public class PDFExporter extends Exporter {

    @Override
    public void exportData(StudentTable studentTable) throws IOException {
        if (studentTable.getTableData() == null || studentTable.getTableData().isEmpty()) {
        }
    }
}
