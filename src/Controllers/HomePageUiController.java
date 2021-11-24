package Controllers;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javaClasses.sqlManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomePageUiController implements Initializable
{
    @FXML
    private TableView<TblView> employeePortal;
    @FXML
    private TableColumn<TblView,String> Name;
    @FXML
    private TableColumn<TblView,String> Location;
    @FXML
    private TableColumn<TblView,String> dept_Name;
    @FXML
    private TableColumn<TblView,String> Emp_Id;


    @FXML
    private Button update_btn;

    private sqlManager currentCon;

    ObservableList<TblView> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        currentCon = new sqlManager();
        try {
            currentCon.returnDBdata(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Location.setCellValueFactory(new PropertyValueFactory<>("location"));
        dept_Name.setCellValueFactory(new PropertyValueFactory<>("department"));
        Emp_Id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));

        employeePortal.setItems(obList);
    }

    public void updateView(ActionEvent actionEvent)
    {
        currentCon = new sqlManager();
        obList = FXCollections.observableArrayList();
        try {
            currentCon.returnDBdata(obList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Location.setCellValueFactory(new PropertyValueFactory<>("location"));
        dept_Name.setCellValueFactory(new PropertyValueFactory<>("department"));
        Emp_Id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));

        employeePortal.setItems(obList);
    }
}
