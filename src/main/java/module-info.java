module com.example.dblab01 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dblab01 to javafx.fxml;
    exports com.example.dblab01;
}