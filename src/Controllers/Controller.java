package Controllers;
import java.sql.*;
import javaClasses.sqlManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller
{
    private sqlManager currentManager;
    @FXML
    private Button LoginBtn;

    public void Query(ActionEvent ae)
    {
        Connection currentConnection;
        sqlManager a = new sqlManager();
        System.out.println(a.returnQuery());
    }
}