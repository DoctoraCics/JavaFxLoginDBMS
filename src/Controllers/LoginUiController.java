package Controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javaClasses.sqlManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
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
            srcWin.centerOnScreen();
            srcWin.show();
        }
        if(aw.getSource().equals(LogInBtn))
        {
            if(!containsIllegalcharacters(userName.getText().trim()))
            {

                if(checkDbMatch())
                {
                    //Load first the FXML
                    FXMLLoader loadNew = new FXMLLoader();
                    loadNew.setLocation(getClass().getResource("/fxml/Main.fxml"));

                    //Get the controller
                    Parent viewParent = loadNew.load();
                    MainUiController mainUi = loadNew.getController();

                    //Send data to new Controller
                    mainUi.setCurrentuser(userName.getText().trim());
                    Scene viewScene = new Scene(viewParent);

                    //Switch view
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
        try
        {
            currentManager = new sqlManager();
            return currentManager.validateLogin(userName.getText().trim(),passWord.getText().trim());
        }catch (SQLException e)
        {
            System.out.println("Sql Exception Thrown");
            e.printStackTrace();
            return false;
        }
    }

    public void invalidWindow() throws IOException
    {
        FXMLLoader loadthis = new FXMLLoader();
        loadthis.setLocation(getClass().getResource("/fxml/invalidDialogPrompt.fxml"));

        DialogPane loadtheError = loadthis.load();
        Dialog<ButtonType> dialog = new Dialog<>();

        Stage puticon = (Stage)dialog.getDialogPane().getScene().getWindow();
        puticon.getIcons().add(new Image(this.getClass().getResource("/Pictures/5.png").toString()));

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