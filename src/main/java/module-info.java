module com.example.combinatorial_optimization {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.combinatorial_optimization.MainPackage to javafx.fxml;
    exports com.example.combinatorial_optimization.MainPackage;
    exports com.example.combinatorial_optimization;
    opens com.example.combinatorial_optimization to javafx.fxml;
    exports com.example.combinatorial_optimization.DataReader;
    opens com.example.combinatorial_optimization.DataReader to javafx.fxml;
    exports com.example.combinatorial_optimization.Settings;
    opens com.example.combinatorial_optimization.Settings to javafx.fxml;

}