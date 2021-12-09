package javaClasses;

import dataStructure.DoubleLinkedListCircle;
import dataStructure.NodeDLL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

public class sqlManager
{
    private String username;
    private String password;
    private String url;
    private Connection currentConnection;

    public sqlManager() throws SQLException
    {
        this.username = "USER"; //change this baka iba username
        this.password = "S1Em$e*r#23"; //change this baka iba password
        this.url = "jdbc:mysql://localhost:3306/caliyxdb";
        this.currentConnection = DriverManager.getConnection(this.url,this.username,this.password);
    }

    public boolean insertIntoUserData(userData insertThis)
    {
        try
        {
            String callStatement = "CALL caliyxdb.createnewAccount(?, ?, ?, ?, ?, ?, ?);";

            hashingValidateClass hashPass = new hashingValidateClass();

            CallableStatement calledStatement = currentConnection.prepareCall(callStatement);
            calledStatement.setLong(1,insertThis.getContactNo());
            calledStatement.setString(2,insertThis.getFirstName());
            calledStatement.setString(3,insertThis.getLastName());
            calledStatement.setString(4,insertThis.getBirthDate());
            calledStatement.setString(5,insertThis.gethome_Address());
            calledStatement.setString(6, insertThis.getEmailAddress());
            calledStatement.setString(7,hashPass.hashThePass(insertThis.getPassWord()));
            calledStatement.executeUpdate();

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
        try
        {
            String statemenT = "CALL selectLogin(?)";
            CallableStatement calledStatement = currentConnection.prepareCall(statemenT);
            calledStatement.setString(1,username);

            //Do not past "query" in the executeQuery as the ? is passed
            ResultSet retrievedData = calledStatement.executeQuery();

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

    public DoubleLinkedListCircle<referenceNumber> inquireInsertion(DoubleLinkedListCircle<houseDetails> tobeInserted)
    {
        DoubleLinkedListCircle<referenceNumber> returnThis = new DoubleLinkedListCircle<>();
        try
        {
            String query = "CALL caliyxdb.insertRefNumber(?,?);";
            int min = 100000;
            int max = 999999;
            int iteration = tobeInserted.getNodeCounter();
            NodeDLL<houseDetails> currentSelected = tobeInserted.getHead();
            while(0 < iteration)
            {
                CallableStatement statement = this.currentConnection.prepareCall(query);
                int generateRandomRef = ThreadLocalRandom.current().nextInt(min, max +1);
                statement.setInt(1,generateRandomRef);
                statement.setInt(2,currentSelected.info.getHouseId());
                statement.executeUpdate();

                referenceNumber addThis = new referenceNumber(generateRandomRef);
                returnThis.addToHead(addThis);
                --iteration;
                currentSelected = currentSelected.next;
            }
            return returnThis;
        }
        catch(SQLException abjsba)
        {
            abjsba.printStackTrace();
            return returnThis;
        }
    }

    public DoubleLinkedListCircle<houseDetails> returnResultInquiry(groupQuery theQuery)
    {
        DoubleLinkedListCircle<houseDetails> returnResult = new DoubleLinkedListCircle<houseDetails>();
        String searchQuery = "";

        try
        {
            searchQuery = "CALL caliyxdb.searchHouse(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            CallableStatement statement = currentConnection.prepareCall(searchQuery);

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

            while(rs.next() && returnResult.getNodeCounter() <= 10)
            {
                String location = rs.getString("area_name") + " " + rs.getString("province_name");
                houseDetails enList = new houseDetails(rs.getInt("floor_no"),rs.getInt("bedroom_no")
                ,rs.getInt("kitchen_no"),rs.getInt("bathroom_no"),rs.getInt("pool_yes_no")
                ,rs.getInt("garage__yes_no"),rs.getInt("price"),rs.getInt("house_id"), location);
                returnResult.addToHead(enList);
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
        return returnResult;
    }
}