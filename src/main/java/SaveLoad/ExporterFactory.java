package SaveLoad;

import studentData.StudentTable;

import static SaveLoad.ExportFormat.CSV;
import static SaveLoad.ExportFormat.PDF;

public class ExporterFactory {
        public static Exporter getExporter (ExportFormat format) {
            switch (format) {
                case CSV:
                    return new CSVExporter();
                case PDF:
                    return new PDFExporter();
                default:
                    throw new IllegalArgumentException("Unsupported export format: " + format);
            }
    }
}
