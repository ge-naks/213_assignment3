module com.example.assingment3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assingment3 to javafx.fxml;
    exports com.example.assingment3;
}