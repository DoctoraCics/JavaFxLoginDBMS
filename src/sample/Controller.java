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
        Connection currentConnection;
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
        password = "Tamay0net!"; //change this baka iba password
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
