module com.example.visualcpu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.visualcpu to javafx.fxml;
    exports com.example.visualcpu;
    exports com.example.visualcpu.visual;
    opens com.example.visualcpu.visual to javafx.fxml;
    exports com.example.visualcpu.cpu;
    opens com.example.visualcpu.cpu to javafx.fxml;
}