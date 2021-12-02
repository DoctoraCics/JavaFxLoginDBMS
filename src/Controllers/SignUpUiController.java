package Controllers;

import javaClasses.sqlManager;
import javaClasses.userData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


public class SignUpUiController
{
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker birthDate;
    @FXML
    private TextField contactNo;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField unitHouseNo;
    @FXML
    private TextField streetD;
    @FXML
    private TextField citY;
    @FXML
    private TextField barangaY;
    @FXML
    private TextField provincE;
    @FXML
    private TextField regioN;
    @FXML
    private TextField postalCode;

    @FXML
    private Button continuE;
    @FXML
    private Button canceL;


    public void changeScrn(ActionEvent e) throws IOException, SQLException
    {
        if(e.getSource().equals(continuE))
        {
           checkInvalidInput(e);
        }
        if(e.getSource().equals(canceL))
        {
            Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/LoginUi.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
            srcWin.setScene(viewScene);
            srcWin.show();
        }
    }

    private void checkInvalidInput(ActionEvent e) throws IOException, SQLException
    {
        String checkFname = firstName.getText().trim();
        String checkLname = lastName.getText().trim();
        String checkBday = "";
        String checkEAddress = emailAddress.getText().trim();
        int checkContactno = -1;
        int checkhouseNo = -1;
        String checkStreet = streetD.getText().trim();
        String checkCity = citY.getText().trim();
        String checkBarangay = barangaY.getText().trim();
        String checkProvince = provincE.getText().trim();
        String checkRegion = regioN.getText().trim();
        int checkPostalcode = -1;

        boolean gotoMain = true;
        if(checkFname ==""|| containsIllegalcharacters(checkFname))gotoMain = false;
        if(checkLname =="" || containsIllegalcharacters(checkLname))gotoMain = false;

        try{checkBday = birthDate.getValue().toString();}catch (Exception z) {gotoMain = false;}

        try{checkContactno = Integer.parseInt(contactNo.getText().trim());}catch(Exception a){gotoMain = false;}
        if(checkContactno < 0) gotoMain = false;

        if(checkEAddress == "" || containsIllegalcharacters(checkEAddress)) gotoMain = false;

        try{checkhouseNo = Integer.parseInt(unitHouseNo.getText().trim());}catch(Exception ab){gotoMain = false;}
        if(checkhouseNo < 0) gotoMain = false;

        if(checkStreet == "" || containsIllegalcharacters(checkStreet)) gotoMain = false;
        if(checkCity == "" || containsIllegalcharacters(checkCity)) gotoMain = false;
        if(checkBarangay == "" || containsIllegalcharacters(checkBarangay)) gotoMain = false;
        if(checkProvince == "" || containsIllegalcharacters(checkProvince)) gotoMain = false;
        if(checkRegion == "" || containsIllegalcharacters(checkRegion)) gotoMain = false;

        try{checkPostalcode = Integer.parseInt(postalCode.getText().trim());}catch(Exception ac){gotoMain = false;}
        if(checkPostalcode < 0){gotoMain = false;}


        if(gotoMain)
        {
            userData newUser = new userData(checkFname,checkLname,checkBday,
                    checkContactno,checkEAddress,checkhouseNo,
                    checkStreet,checkCity,checkBarangay,checkProvince,
                    checkRegion,checkPostalcode);

            changetoMain(e, newUser);
        }
        else
        {
            launchInvalidwindow();
        }
    }

    private void changetoMain(ActionEvent e, userData insert) throws IOException, SQLException
    {
        //Insertion to database of user data
        userData insertThisNewUser = insert;
        sqlManager insertOrder = new sqlManager();
        insertOrder.insertIntoUserData(insertThisNewUser);

        //Load first the FXML
        FXMLLoader loadNew = new FXMLLoader();
        loadNew.setLocation(getClass().getResource("/fxml/Main.fxml"));

        //Get the controller
        Parent viewParent = loadNew.load();
        MainUiController mainUi = loadNew.getController();

        //Send data to new Controller
        mainUi.setCurrentuser(insertThisNewUser);
        Scene viewScene = new Scene(viewParent);

        //Switch view
        Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
        srcWin.setScene(viewScene);
        srcWin.show();
    }

    private void launchInvalidwindow() throws IOException
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
            firstName.clear();
            lastName.clear();
            contactNo.clear();
            emailAddress.clear();
            unitHouseNo.clear();
            streetD.clear();
            citY.clear();
            barangaY.clear();
            regioN.clear();
            postalCode.clear();
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
