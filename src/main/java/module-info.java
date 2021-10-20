module com.example.combinatorial_optimization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.combinatorial_optimization to javafx.fxml;
    exports com.example.combinatorial_optimization;
}