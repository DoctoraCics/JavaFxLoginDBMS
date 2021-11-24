package javaClasses;

import Controllers.TblView;
import javafx.collections.ObservableList;

import java.sql.*;

public class sqlManager
{

    private String username;
    private String password;
    private String url;
    Connection currentConnection;

    public sqlManager()
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

    public void returnDBdata(ObservableList oblist) throws SQLException
    {
        currentConnection = DriverManager.getConnection(url,username,password);
        ResultSet rs = currentConnection.createStatement().executeQuery("SELECT * FROM tbl_employees");
        while (rs.next())
        {
            oblist.add(new TblView(rs.getString("employee_id"),rs.getString("department"),rs.getString("location"),rs.getString("name")));
        }
    }
}
