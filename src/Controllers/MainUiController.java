package Controllers;

import javaClasses.userData;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;

public class MainUiController
{
    private userData currentuser;

    //Number of Floors
    @FXML
    private CheckBox oneStory;
    @FXML
    private CheckBox twoStory;
    @FXML
    private CheckBox threeStory;
    @FXML
    private CheckBox fourStory;
    private ToggleGroup floorToggleGroup;

    //Bedrooms
    @FXML
    private CheckBox twoBedroom;
    @FXML
    private CheckBox fourBedroom;
    @FXML
    private CheckBox sixBedroom;
    @FXML
    private CheckBox sevenBedroom;

    //Kitchens
    @FXML
    private CheckBox twoKitchen;
    @FXML
    private CheckBox fourKitchen;
    @FXML
    private CheckBox fiveKitchen;

    //Bathrooms
    @FXML
    private CheckBox twoBathrooms;
    @FXML
    private CheckBox fourBathrooms;
    @FXML
    private CheckBox fiveBathrooms;


    //Pools yes or no
    @FXML
    private CheckBox yesPool;
    @FXML
    private CheckBox noPool;

    //Garage yes or no
    @FXML
    private CheckBox yesGarage;
    @FXML
    private CheckBox noGarage;

    //Price Ranges
    @FXML
    private CheckBox fourMillion;
    @FXML
    private CheckBox sevenMillion;
    @FXML
    private CheckBox tenMillion;
    @FXML
    private CheckBox fifteenMillion;

    public void setCurrentuser(userData a)
    {
        this.currentuser = a;
        System.out.println(currentuser.getCompiled());
    }
}
