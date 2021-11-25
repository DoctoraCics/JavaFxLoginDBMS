package Controllers;
import java.io.IOException;
import java.sql.*;
import javaClasses.sqlManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginUiController
{
    private sqlManager currentManager;
    @FXML
    private Button LoginBtn;

    public void ChangeScrn(ActionEvent aw) throws IOException
    {
        if(checkDbMatch())
        {
            Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/HomePageUi.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage srcWin = (Stage)((Node)aw.getSource()).getScene().getWindow();
            srcWin.setScene(viewScene);
            srcWin.show();
        }
        else
        {

        }
    }

    public boolean checkDbMatch()
    {
        boolean condition = true;
        return condition;
    }
}