package javaClasses;

import Controllers.TblView;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

public class sqlManager
{
    private String username;
    private String password;
    private String url;
    private Connection currentConnection;

    public sqlManager()
    {
        username = "root"; //change this baka iba username
        password = "admin"; //change this baka iba password
        url = "jdbc:mysql://localhost:3306/employeeportal"; //change maybe if it does not work?
    }

    public sqlManager(String url)
    {
        username = "root";
        password = "admin";
        url = "jdbc:mysql://localhost:3306/" + url;
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

    public boolean insertIntoUserData(userData insertThis) throws SQLException
    {
        String query = "INSERT INTO [columns you want to select] FROM [what table?]";
        currentConnection = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = currentConnection.prepareStatement(query);


        return true;
    }

    public boolean validateLogin(String username, String pass) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        String query = "SELECT [] FROM [] WHERE []=" + username;
        PreparedStatement statement = currentConnection.prepareStatement(query);

        ResultSet retrievedData = statement.executeQuery(query);
        String retrievedHash = retrievedData.getString(1);
        String retrievedEmail = retrievedData.getString(0);

        hashingValidateClass validation = new hashingValidateClass();
        boolean passWordcondition = validation.validatePassword(pass,retrievedHash);
        boolean emailMatch = username.compareTo(retrievedEmail) == 0;

        return passWordcondition && emailMatch;
    }

    public ObservableList returnDBdata(ObservableList oblist) throws SQLException
    {
        currentConnection = DriverManager.getConnection(url,username,password);
        ResultSet rs = currentConnection.createStatement().executeQuery("SELECT * FROM tbl_employees");
        ObservableList editingList = oblist;
        while (rs.next())
        {
            editingList.add(new TblView(rs.getString("employee_id"),rs.getString("department"),rs.getString("location"),rs.getString("name")));
        }
        return editingList;
    }
}
