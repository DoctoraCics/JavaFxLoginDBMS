package Controllers;

import javaClasses.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainUiController
{
    private userData currentuser;

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
        if(e.getSource().equals(logOut))
        {
            Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/LoginUi.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
            srcWin.setScene(viewScene);
            srcWin.show();
        }
        if(e.getSource().equals(continuE))
        {
            sqlManager searching = new sqlManager();

            groupQuery searchThis = initializeQuery();
            houseDetails resultList[] = searching.returnResultInquiry(searchThis);

            //Load first the FXML
            FXMLLoader loadNew = new FXMLLoader();
            loadNew.setLocation(getClass().getResource("/fxml/Result.fxml"));

            //Get the controller
            Parent viewParent = loadNew.load();
            ResultUiController mainUi = loadNew.getController();

            //Send data to new Controller
            mainUi.setHouseDetails(resultList);
            Scene viewScene = new Scene(viewParent);

            //Switch view
            Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
            srcWin.setScene(viewScene);
            srcWin.show();
        }
    }

    private groupQuery initializeQuery()
    {
        //groupQuery search = new groupQuery(1,1,1,1,1,1,0,0,1,1);
        groupQuery search = new groupQuery();

        System.out.println(floors.getValue().toString());
        switch (floors.getValue().toString())
        {
            case "1-2 Floors":
            {
                search.setFloorRange(1);
                search.setFloorRange2(2);
            }
            case "3 Floors":
            {
                search.setFloorRange(3);
                search.setFloorRange2(3);
            }
            case "4+ Floors":
            {
                search.setFloorRange(4);
                search.setFloorRange2(4);
            }
        }
        switch (bedrooms.getValue().toString())
        {
            case "1-2 Bedrooms":
            {
                search.setBedRoomRange(1);
                search.setBedRoomRange(2);
            }
            case "3-4 Bedrooms":
            {
                search.setBedRoomRange(3);
                search.setBedRoomRange(4);
            }
            case "5 bedrooms":
            {
                search.setBedRoomRange(5);
                search.setBedRoomRange2(5);
            }
        }
        switch(kitchens.getValue().toString())
        {
            case "1 kitchen":
            {
                search.setKitchenNo(1);
            }
            case "2 kitchens":
            {
                search.setKitchenNo(2);
            }
            case "3 kitchen":
            {
                search.setKitchenNo(3);
            }
        }
        switch (bathrooms.getValue().toString())
        {
            case "1 Bathroom":
            {
                search.setBathroomNo(1);
            }
            case "2 Bathrooms":
            {
                search.setBathroomNo(2);
            }
            case "3 Bathrooms":
            {
                search.setBathroomNo(3);
            }
        }
        switch (pool.getValue().toString())
        {
            case "Yes":
                search.setPool(1);
            default:
                search.setPool(0);
        }
        switch (garage.getValue().toString())
        {
            case "Yes":
                search.setGarage(1);
            default:
                search.setGarage(0);
        }
        switch (prices.getValue().toString())
        {
            case "4 million":
            {
                search.setPriceRange(1000000);
                search.setPriceRange2(5000001);
            }
            case "7 millions":
            {
                search.setPriceRange(4000001);
                search.setPriceRange2(8000000);
            }
            case "10 millions":
            {
                search.setPriceRange(8000001);
                search.setPriceRange2(10000001);

            }
        }
        System.out.println(search);
        return search;
    }

    public void setCurrentuser(userData a)
    {
        this.currentuser = a;
        System.out.println(currentuser.getCompiled());
    }
}
