package Controllers;

import dataStructure.DoubleLinkedListCircle;
import javaClasses.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MainUiController
{
    private String currentuser;

    private DoubleLinkedListCircle<houseDetails> resultList;

    @FXML
    private Button logOut;
    @FXML
    private Button continuE;

    @FXML
    private ComboBox floors;

    @FXML
    private ComboBox bedrooms;

    @FXML
    private ComboBox kitchens;

    @FXML
    private ComboBox bathrooms;

    @FXML
    private ComboBox pool;

    @FXML
    private ComboBox garage;

    @FXML
    private ComboBox prices;


    public void initialize()
    {
        floors.getItems().addAll("1-2 Floors", "3 Floors", "4 Floors");
        floors.setValue("1-2 Floors");

        bedrooms.getItems().addAll("1-2 Bedrooms", "3-4 Bedrooms", "5 bedrooms");
        bedrooms.setValue("1-2 Bedrooms");

        kitchens.getItems().addAll("1 kitchen", "2 kitchens", "3 kitchen");
        kitchens.setValue("1 kitchen");

        bathrooms.getItems().addAll("1 Bathroom", "2 Bathrooms", "3 Bathrooms");
        bathrooms.setValue("1 Bathroom");

        pool.getItems().addAll("Yes", "No");
        pool.setValue("No");

        garage.getItems().addAll("Yes", "No");
        garage.setValue("No");

        prices.getItems().addAll("4 million", "7 millions", "10 million");
        prices.setValue("4 million");
    }

    public void changScrn(ActionEvent e) throws IOException
    {
        if(e.getSource().equals(this.logOut))
        {
            Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/LoginUi.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
            srcWin.setScene(viewScene);
            srcWin.show();
        }
        if(e.getSource().equals(this.continuE))
        {
            try
            {
                sqlManager searching = new sqlManager();

                groupQuery searchThis = initializeQuery();
                this.resultList = searching.returnResultInquiry(searchThis);
                if(!resultList.isEmpty() && e.getSource().equals(continuE))
                {
                    //Load first the FXML
                    FXMLLoader loadNew = new FXMLLoader();
                    loadNew.setLocation(getClass().getResource("/fxml/Result.fxml"));

                    //Get the controller
                    Parent viewParent = loadNew.load();
                    ResultUiController mainUi = loadNew.getController();

                    //Send data to new Controller
                    mainUi.setHouseDetails(resultList, this.currentuser);
                    Scene viewScene = new Scene(viewParent);

                    //Switch view
                    Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
                    srcWin.setScene(viewScene);
                    srcWin.show();
                }
                else
                {
                    dialogLaunch();
                }
            }
            catch (SQLException abc)
            {
                dialogLaunch();
            }
        }
    }

    private groupQuery initializeQuery()
    {
        groupQuery search = new groupQuery();
        switch (floors.getValue().toString())
        {
            case "1-2 Floors":
            {
                search.setFloorRange(1);
                search.setFloorRange2(2);
                break;
            }
            case "3 Floors":
            {
                search.setFloorRange(3);
                search.setFloorRange2(3);
                break;
            }
            case "4 Floors":
            {
                search.setFloorRange(4);
                search.setFloorRange2(4);
                break;
            }
        }
        switch (bedrooms.getValue().toString())
        {
            case "1-2 Bedrooms":
            {
                search.setBedRoomRange(1);
                search.setBedRoomRange2(2);
                break;
            }
            case "3-4 Bedrooms":
            {
                search.setBedRoomRange(3);
                search.setBedRoomRange2(4);
                break;
            }
            case "5 bedrooms":
            {
                search.setBedRoomRange(5);
                search.setBedRoomRange2(5);
                break;
            }
        }
        switch(kitchens.getValue().toString())
        {
            case "1 kitchen":
            {
                search.setKitchenNo(1);
                break;
            }
            case "2 kitchens":
            {
                search.setKitchenNo(2);
                break;
            }
            case "3 kitchen":
            {
                search.setKitchenNo(3);
                break;
            }
        }
        switch (bathrooms.getValue().toString())
        {
            case "1 Bathroom":
            {
                search.setBathroomNo(1);
                break;
            }
            case "2 Bathrooms":
            {
                search.setBathroomNo(2);
                break;
            }
            case "3 Bathrooms":
            {
                search.setBathroomNo(3);
                break;
            }
        }
        switch (pool.getValue().toString())
        {
            case "Yes":
                search.setPool(1);
                break;
            default:
                search.setPool(0);
        }
        switch (garage.getValue().toString())
        {
            case "Yes":
                search.setGarage(1);
                break;
            default:
                search.setGarage(0);
        }
        switch (prices.getValue().toString())
        {
            case "4 million":
            {
                search.setPriceRange(1000000);
                search.setPriceRange2(5000001);
                break;
            }
            case "7 millions":
            {
                search.setPriceRange(4000001);
                search.setPriceRange2(8000000);
                break;
            }
            case "10 millions":
            {
                search.setPriceRange(8000001);
                search.setPriceRange2(20000001);
                break;
            }
        }
        return search;
    }

    public void setCurrentuser(String a)
    {
        this.currentuser = a;
    }

    public void dialogLaunch() throws IOException
    {
        FXMLLoader loadthis = new FXMLLoader();
        loadthis.setLocation(getClass().getResource("/fxml/noMatchFoundDialogPrompt.fxml"));

        DialogPane loadtheError = loadthis.load();
        Dialog<ButtonType> dialog = new Dialog<>();

        Stage puticon = (Stage)dialog.getDialogPane().getScene().getWindow();
        puticon.getIcons().add(new Image(this.getClass().getResource("/Pictures/5.png").toString()));

        dialog.setDialogPane(loadtheError);
        dialog.setTitle("Error");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
        if(clickedButton.get() == ButtonType.CLOSE)
        {
            dialog.close();
        }
    }
}
