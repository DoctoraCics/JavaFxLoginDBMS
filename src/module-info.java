module JavaFXLogin
{
    requires javafx.controls; // Required import
    requires javafx.fxml;
    requires java.sql; // Required import
    opens Controllers; //package you want to open
    opens javaClasses;
}