package Controllers;

import javaClasses.hashingGenVal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class devUiController
{
    //Generate Hash Tab
    @FXML
    private Button hashBtn;
    @FXML
    private TextField hashTxtFld;
    @FXML
    private TextArea outPutField;


    //Validate Tab
    @FXML
    private TextArea resultTest;
    @FXML
    private Button validateBtn;
    @FXML
    private TextField inputPass;

    private hashingGenVal secuRity;

    private String storedHashPassword;


    public void hashIt() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String storePass = hashTxtFld.getText();
        String hashedVersion = secuRity.hashThePass(storePass);
        this.storedHashPassword = hashedVersion;
        outPutField.setText(hashedVersion);
    }

    //The most used algorithm as users would login most of the time
    public void validateIt() throws  NoSuchAlgorithmException, InvalidKeySpecException
    {
        String inputtedPass = inputPass.getText();
        boolean validation = secuRity.validatePassword(inputtedPass,storedHashPassword);
        String output = inputtedPass + " : " +  validation + " : " + storedHashPassword;
        resultTest.setText(output);
    }
}
