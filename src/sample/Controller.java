package sample;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.xml.transform.Result;


public class Controller
{
    private sqlManager currentManager;
    @FXML
    private Button test;


    public void test(ActionEvent ae)
    {
        String username = "root";
        String password = "S1Em$e*r#23";
        Connection currentConnection;
        /*
        String url = "jdbc:mysql://localhost:3306/employeeportal";
        String query = "SELECT * FROM tbl_employees";
        String compiled = "";
        try
        {
            currentConnection = DriverManager.getConnection(url,username,password);
            Statement statement = currentConnection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next())
            {
                String name = result.getString(1);
                compiled += "name+\n";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(compiled);*/

        sqlManager a = new sqlManager();
        System.out.println(a.returnQuery());
    }
}
class sqlManager
{
    private String username;
    private String password;
    private String url;
    Connection currentConnection;
    sqlManager()
    {
        username = "root"; //change this baka iba username
        password = "S1Em$e*r#23"; //change this baka iba password
        url = "jdbc:mysql://localhost:3306/employeeportal"; //change maybe if it does not work?
    }
    public String returnQuery()
    {
        String compiled = "";
        String query = "SELECT * FROM tbl_employees";
        try
        {
            currentConnection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement = currentConnection.prepareStatement(query);
            ResultSet result = statement.executeQuery(query);

            while(result.next())
            {
                String name = result.getString(4);
                compiled += name +" \n";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return compiled;
    }
}
