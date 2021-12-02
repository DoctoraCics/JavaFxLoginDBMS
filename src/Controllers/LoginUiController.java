package Controllers;
import java.io.IOException;

import javaClasses.sqlManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class LoginUiController
{
    private sqlManager currentManager;
    @FXML
    private Hyperlink SignUpHyper;
    @FXML
    private Button LogInBtn;

    public void ChangeScrn(ActionEvent aw) throws IOException
    {
        if(aw.getSource().equals(SignUpHyper))
        {
            Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage srcWin = (Stage)((Node)aw.getSource()).getScene().getWindow();
            srcWin.setScene(viewScene);
            srcWin.show();
        }
        if(aw.getSource().equals(LogInBtn))
        {
            if(checkDbMatch())
            {
                Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
                Scene viewScene = new Scene(viewParent);

                Stage srcWin = (Stage)((Node)aw.getSource()).getScene().getWindow();
                srcWin.setScene(viewScene);
                srcWin.show();
            }
        }
    }

    private boolean checkDbMatch()
    {
        boolean condition = false;
        return condition;
    }
}