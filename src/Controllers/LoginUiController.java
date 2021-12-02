package Controllers;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javaClasses.sqlManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginUiController
{
    private sqlManager currentManager;
    @FXML
    private Hyperlink SignUpHyper;
    @FXML
    private Button LogInBtn;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;


    public void ChangeScrn(ActionEvent aw) throws IOException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
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

    private boolean checkDbMatch() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        currentManager = new sqlManager();
        return currentManager.validateLogin(userName.getText(),passWord.getText());
    }
}