module com.example.visualcpu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;

    requires java.naming;
    requires sqlite.dialect;

    opens com.example.visualcpu to javafx.fxml;
    exports com.example.visualcpu;
    exports com.example.visualcpu.visualAndDAO;
    opens com.example.visualcpu.visualAndDAO;
    exports com.example.visualcpu.cpu;
    opens com.example.visualcpu.cpu to javafx.fxml;
}