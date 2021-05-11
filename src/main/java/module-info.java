module com.mycompany.qlsv {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.qlsv to javafx.fxml;
    exports com.mycompany.qlsv;
    exports com.mycompany.pojo;
}
