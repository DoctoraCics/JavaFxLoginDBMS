package javaClasses;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Arrays;

public class sqlManager
{
    private String username;
    private String password;
    private String url;
    private Connection currentConnection;

    public sqlManager()
    {
        username = "USER"; //change this baka iba username
        password = "S1Em$e*r#23"; //change this baka iba password
        url = "jdbc:mysql://localhost:3306/caliyxdb"; //change maybe if it does not work?
    }

    public sqlManager(String url)
    {
        username = "USER";
        password = "S1Em$e*r#23";
        url = "jdbc:mysql://localhost:3306/" + url;
    }

    public boolean insertIntoUserData(userData insertThis)
    {
        try
        {
            String query = "INSERT INTO `caliyxdb`.`user_table` (`contact_num`, `fname`, `lname`, `bday`, `home_address`, `email_address`) VALUES (?, ?, ?, ?, ?, ?);";
            String query2 = "INSERT INTO `caliyxdb`.`user_login` (`email_address`, `password`) VALUES (?, ?);";
            currentConnection = DriverManager.getConnection(this.url,this.username,this.password);
            hashingValidateClass hashPass = new hashingValidateClass();

            PreparedStatement statement2 = currentConnection.prepareStatement(query2);
            statement2.setString(1,insertThis.getEmailAddress());
            statement2.setString(2,hashPass.hashThePass(insertThis.getPassWord()));


            PreparedStatement statement = currentConnection.prepareStatement(query);
            statement.setInt(1,insertThis.getContactNo());
            statement.setString(2,insertThis.getFirstName());
            statement.setString(3,insertThis.getLastName());
            statement.setString(4,insertThis.getBirthDate());
            statement.setString(5,insertThis.gethome_Address());
            statement.setString(6, insertThis.getEmailAddress());

            statement.executeUpdate();
            statement2.executeUpdate();

        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (InvalidKeySpecException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean validateLogin(String username, String pass)
    {
        //This version is very dangerous
        //String query = "SELECT * FROM caliyxdb.user_login WHERE email_address=" + "'" + username + "'";

        try
        {
            currentConnection = DriverManager.getConnection(this.url,this.username,this.password);

            String query = "SELECT * FROM caliyxdb.user_login WHERE email_address = ?";

            PreparedStatement statement = currentConnection.prepareStatement(query);
            statement.setString(1, username);

            //Do not past "query" in the executeQuery as the ? is passed
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

            hashingValidateClass validation = new hashingValidateClass();
            passWordcondition = validation.validatePassword(pass,retrievedHash);
            emailMatch = username.compareTo(retrievedEmail) == 0;
            return passWordcondition && emailMatch;
        }catch(SQLException e){return false;}
        catch(Exception e){return false;}
    }

    public houseDetails[] returnResultInquiry(groupQuery theQuery)
    {
        houseDetails[] returnResult = new houseDetails[3];
        String searchQuery = "";
        try
        {
            searchQuery = "SELECT floor_no,bedroom_no,kitchen_no,bathroom_no,pool_yes_no,garage__yes_no,price\n" +
                    ",barangay,province_id FROM caliyxdb.house_list WHERE\n" +
                    " (floor_no = ? OR floor_no = ?) AND\n" +
                    " (bedroom_no = ? OR bedroom_no = ?) AND\n" +
                    " (kitchen_no = ?) AND\n" +
                    " (bathroom_no = ?) AND\n" +
                    " (pool_yes_no = ?) AND\n" +
                    " (garage__yes_no = ?) AND\n" +
                    " (price > ? OR price < ?);";
            PreparedStatement statement = currentConnection.prepareStatement(searchQuery);
            statement.setInt(1,theQuery.getFloorRange());
            statement.setInt(2,theQuery.getFloorRange2());

            statement.setInt(3, theQuery.getBedRoomRange());
            statement.setInt(4,theQuery.getBedRoomRange2());

            statement.setInt(5, theQuery.getKitchenNo());
            statement.setInt(6, theQuery.getBathroomNo());
            statement.setInt(7, theQuery.isPool());
            statement.setInt(8,theQuery.isGarage());

            statement.setInt(9,theQuery.getPriceRange());
            statement.setInt(10, theQuery.getPriceRange2());

            ResultSet rs = statement.executeQuery();

            for(int i = 0; i< returnResult.length; i++)
            {
                if(rs.next())
                {
                    houseDetails a = new houseDetails(rs.getInt("floor_no"),
                            rs.getInt("bedroom_no"),
                            rs.getInt("kitchen_no"),
                            rs.getInt("bathroom_no"),
                            rs.getInt("pool_yes_no"),
                            rs.getInt("garage_yes_no"),
                            rs.getInt("price"));

                    returnResult[i] = a;
                }

            }

        }catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("sql exception at sql class");
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            System.out.println("Nullpointer in sql class");
        }
        //System.out.println(returnResult[0].toString());
        return returnResult;
    }

/*
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
*/
/* Different Code
    public String returnQuery()
    {
        String compiled = "";
        try
        {

            String query = "SELECT * FROM tbl_employees";
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

*/
        /*
    public boolean deleteAccountUser(userData deleteThis)
    {
        try
        {
            String deleteUpdateLogin = "DELETE FROM `caliyxdb`.`user_login` WHERE (`email_address` = ?);";
            String deleteUserdata = "DELETE FROM `caliyxdb`.`user_table` WHERE (`contact_num` = ?);";
            currentConnection = DriverManager.getConnection(this.url,this.username,this.password);

            PreparedStatement statement = currentConnection.prepareStatement(deleteUpdateLogin);
            statement.setString(1,deleteThis.getEmailAddress());

            PreparedStatement statement2 = currentConnection.prepareStatement(deleteUserdata);
            statement2.setInt(1,deleteThis.getContactNo());

            statement.executeUpdate();
            statement2.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    */
}