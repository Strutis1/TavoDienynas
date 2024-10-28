module com.crew.mif.dienynas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jdk.jfr;
    requires org.apache.pdfbox;

    opens com.crew.mif.dienynas to javafx.fxml;
    exports com.crew.mif.dienynas;
    exports studentData;
    opens studentData to javafx.fxml;
    exports helper;
    opens helper to javafx.fxml;
}