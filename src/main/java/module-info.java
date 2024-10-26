module com.crew.mif.dienynas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.crew.mif.dienynas to javafx.fxml;
    exports com.crew.mif.dienynas;
}