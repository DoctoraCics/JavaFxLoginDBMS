package Controllers;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Optional;

import javaClasses.sqlManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
            if(!containsIllegalcharacters(userName.getText().trim()))
            {
                if(checkDbMatch())
                {
                    Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
                    Scene viewScene = new Scene(viewParent);

                    Stage srcWin = (Stage)((Node)aw.getSource()).getScene().getWindow();
                    srcWin.setScene(viewScene);
                    srcWin.show();
                }
                else
                {
                    invalidWindow();
                }
            }
            else
            {
                invalidWindow();
            }
        }
    }

    private boolean checkDbMatch()
    {
        currentManager = new sqlManager();
        return currentManager.validateLogin(userName.getText().trim(),passWord.getText().trim());
    }

    public void invalidWindow() throws IOException
    {
        FXMLLoader loadthis = new FXMLLoader();
        loadthis.setLocation(getClass().getResource("/fxml/invalidDialogPrompt.fxml"));

        DialogPane loadtheError = loadthis.load();
        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setDialogPane(loadtheError);
        dialog.setTitle("Error");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
        if(clickedButton.get() == ButtonType.CLOSE)
        {
            userName.clear();
            passWord.clear();
            dialog.close();
        }
    }

    private boolean containsIllegalcharacters(String candidate)
    {
        String[] arrayofIllegalCharacters = {"\"","/",":","*","?","<",">","|",";"};
        for(int i = 0; i < arrayofIllegalCharacters.length; i++)
        {
            if(candidate.contains(arrayofIllegalCharacters[i]))
            {
                return true;
            }
        }
        return false;
    }
}