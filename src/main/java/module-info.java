module com.romann1c.contracts {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;

    opens com.romann1c.contracts to javafx.fxml;
    exports com.romann1c.contracts;
    exports com.romann1c.contracts.database.purejdbc;
}