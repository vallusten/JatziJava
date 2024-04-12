module com.example.jatzi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jatzi to javafx.fxml;
    exports com.example.jatzi;
}