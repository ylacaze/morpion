module com.example.jfxmorpion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jfxmorpion.morpion to javafx.fxml;
    exports com.example.jfxmorpion.morpion;
}