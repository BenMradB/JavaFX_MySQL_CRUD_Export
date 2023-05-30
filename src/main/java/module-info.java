module com.example.tableview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.tableview to javafx.fxml;
    opens com.example.tableview.models to javafx.base;


    exports com.example.tableview;
}