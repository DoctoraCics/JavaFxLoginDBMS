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
        password = "S1Em$e*r#23"; //change this baka iba password
        url = "jdbc:mysql://localhost:3306/caliyxdb"; //change maybe if it does not work?
    }

    public sqlManager(String url)
    {
        username = "root";
        password = "S1Em$e*r#23";
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
        String query = "INSERT INTO `caliyxdb`.`user_table` (`contact_num`, `fname`, `lname`, `bday`, `home_address`, `email_address`) VALUES (?, ?, ?, ?, ?, ?);";
        currentConnection = DriverManager.getConnection(url,username,password);
        PreparedStatement statement = currentConnection.prepareStatement(query);
        statement.setInt(1,insertThis.getContactNo());
        statement.setString(2,insertThis.getFirstName());
        statement.setString(3,insertThis.getLastName());
        statement.setString(4,insertThis.getBirthDate());
        statement.setString(5,insertThis.gethome_Address());
        statement.setString(6, insertThis.getEmailAddress());
        statement.executeQuery();

        return true;
    }

    public boolean validateLogin(String username, String pass) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        //This version is very dangerous
        //String query = "SELECT * FROM caliyxdb.user_login WHERE email_address=" + "'" + username + "'";

        currentConnection = DriverManager.getConnection(this.url,this.username,this.password);

        String query = "SELECT * FROM caliyxdb.user_login WHERE email_address = ?";

        PreparedStatement statement = currentConnection.prepareStatement(query);
        statement.setString(1, username);

        //Do not past query in the executeQuery as the ? is passed
        ResultSet retrievedData = statement.executeQuery();


        String retrievedHash = "";
        String retrievedEmail = " ";
        boolean passWordcondition = false;
        boolean emailMatch = false;

        while(retrievedData.next())
        {
            retrievedHash = retrievedData.getString("password");
            retrievedEmail = retrievedData.getString("email_address");
        }

        try
        {
            hashingValidateClass validation = new hashingValidateClass();
            passWordcondition = validation.validatePassword(pass,retrievedHash);
            emailMatch = username.compareTo(retrievedEmail) == 0;
        }catch(Exception e){}
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
