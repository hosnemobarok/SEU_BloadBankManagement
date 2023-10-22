module com.example.seu_bloadbankmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.seu_bloadbankmanagement to javafx.fxml;
    exports com.example.seu_bloadbankmanagement;
}