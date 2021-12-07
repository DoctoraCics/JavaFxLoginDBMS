package Controllers;

import dataStructure.DoubleLinkedListCircle;
import dataStructure.NodeDLL;
import javaClasses.houseDetails;
import javaClasses.referenceNumber;
import javaClasses.sqlManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class ResultUiController
{
    private DoubleLinkedListCircle<houseDetails> retrievedHoused = new DoubleLinkedListCircle<>();
    private String currentEmail;
    private NodeDLL<houseDetails> result1;
    private NodeDLL<houseDetails> result2;
    private NodeDLL<houseDetails> result3;

    @FXML
    private TextArea resultField;
    @FXML
    private TextArea resultField2;
    @FXML
    private TextArea resultField3;

    @FXML
    private Button goToPrevious;
    @FXML
    private Button goToSummary;

    @FXML
    private Button inquire1;
    @FXML
    private Button inquire2;
    @FXML
    private Button inquire3;


    public void initialize()
    {
        goToSummary.setDisable(true);
    }


    public void takeAction(ActionEvent e) throws IOException, SQLException
    {
        switch (retrievedHoused.getNodeCounter())
        {
            case 1:
                doubleLinkListCase1(e);break;
            case 2:
                doubleLinkListCase2(e);break;
            default:
                doubleLinkListDefault(e);break;

        }
        if(e.getSource().equals(goToPrevious))
        {
            Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
            srcWin.setScene(viewScene);
            srcWin.centerOnScreen();
            srcWin.show();
        }
        if(e.getSource().equals(goToSummary))
        {
            DoubleLinkedListCircle<houseDetails> transFormed = new DoubleLinkedListCircle<>();
            int iterations = this.retrievedHoused.getNodeCounter();
            NodeDLL<houseDetails> marcher = this.retrievedHoused.getHead();
            while(0 < iterations)
            {
                if(marcher.info.getInquired())
                {
                    transFormed.addToHead(marcher.info);
                }
                marcher = marcher.next;
                --iterations;
            }


            sqlManager insertionOfReceipt = new sqlManager();
            DoubleLinkedListCircle<referenceNumber> receipt = insertionOfReceipt.inquireInsertion(transFormed);

            //Load first the FXML
            FXMLLoader loadNew = new FXMLLoader();
            loadNew.setLocation(getClass().getResource("/fxml/Summary.fxml"));

            //Get the controller
            Parent viewParent = loadNew.load();
            SummaryUiController mainUi = loadNew.getController();

            //Send data to new Controller
            try
            {
                mainUi.setTheController(this.currentEmail, receipt);
                Scene viewScene = new Scene(viewParent);

                //Switch view
                Stage srcWin = (Stage)((Node)e.getSource()).getScene().getWindow();
                srcWin.setTitle("Summary");
                srcWin.setScene(viewScene);
                srcWin.show();
            }catch(NullPointerException a)
            {
                System.out.println("User did not Inquire");
            }
        }
    }

    public void setHouseDetails(DoubleLinkedListCircle<houseDetails> received, String userEmail)
    {
        this.retrievedHoused = received;
        NodeDLL<houseDetails> startPointer = retrievedHoused.getHead();
        result1 = retrievedHoused.getHead();
        result2 = result1.next;
        result3 = result2.next;
        this.currentEmail = userEmail;

        resultField.setText(result1.toString());
        resultField2.setText(result2.toString());
        resultField3.setText(result3.toString());
    }
    private void doubleLinkListCase1(ActionEvent e)
    {
        if(e.getSource().equals(inquire1) || e.getSource().equals(inquire2) || e.getSource().equals(inquire3))
        {
            if(result1.info.getInquired())
            {
                inquire1.setText("Inquire");
                inquire2.setText("Inquire");
                inquire3.setText("Inquire");
                result1.info.setInquireD(false);
                goToSummary.setDisable(true);


            }
            else
            {
                inquire1.setText("Cancel");
                inquire2.setText("Cancel");
                inquire3.setText("Cancel");
                result1.info.setInquireD(true);
                goToSummary.setDisable(false);
            }
        }
    }

    private void doubleLinkListCase2(ActionEvent e)
    {
        if(e.getSource().equals(inquire1) || e.getSource().equals(inquire3))
        {
            if(result1.info.getInquired())
            {
                inquire1.setText("Inquire");
                inquire3.setText("Inquire");
                result1.info.setInquireD(false);
            }
            else
            {
                inquire1.setText("Cancel");
                inquire3.setText("Cancel");
                result1.info.setInquireD(true);
            }
        }
        if(e.getSource().equals(inquire2))
        {
            if(result2.info.getInquired())
            {
                inquire2.setText("Inquire");
                result2.info.setInquireD(false);
            }
            else
            {
                inquire2.setText("Cancel");
                result2.info.setInquireD(true);
            }
        }
        if(result1.info.getInquired() || result2.info.getInquired())
        {
            goToSummary.setDisable(false);
        }
        else
        {
            goToSummary.setDisable(true);

        }
    }

    private void doubleLinkListDefault(ActionEvent e)
    {
        if(e.getSource().equals(inquire1))
        {
            if(result1.info.getInquired())
            {
                inquire1.setText("Inquire");
                result1.info.setInquireD(false);
            }
            else
            {
                inquire1.setText("Cancel");
                result1.info.setInquireD(true);
            }
        }
        if(e.getSource().equals(inquire2))
        {
            if(result2.info.getInquired())
            {
                inquire2.setText("Inquire");
                result2.info.setInquireD(false);
            }
            else
            {
                inquire2.setText("Cancel");
                result2.info.setInquireD(true);
            }
        }
        if(e.getSource().equals(inquire3))
        {
            if(result3.info.getInquired())
            {
                inquire3.setText("Inquire");
                result3.info.setInquireD(false);
            }
            else
            {
                inquire3.setText("Cancel");
                result3.info.setInquireD(true);
            }
        }
        if(result1.info.getInquired() || result2.info.getInquired() || result3.info.getInquired())
        {
            goToSummary.setDisable(false);
        }
        else
        {
            goToSummary.setDisable(true);
        }
    }
}
