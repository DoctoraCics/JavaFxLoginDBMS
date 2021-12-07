package Controllers;

import dataStructure.DoubleLinkedListCircle;
import dataStructure.NodeDLL;
import javaClasses.referenceNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;

public class SummaryUiController
{
    NodeDLL<referenceNumber> receipt1;
    NodeDLL<referenceNumber> receipt2;
    NodeDLL<referenceNumber> receipt3;

    private String email;
    private DoubleLinkedListCircle<referenceNumber> inquireD;

    @FXML
    private TextArea resultField1;
    @FXML
    private TextArea resultField2;
    @FXML
    private TextArea resultField3;

    public void logOutnow(ActionEvent actionEvent) throws IOException
    {
        Parent viewParent = FXMLLoader.load(getClass().getResource("/fxml/LoginUi.fxml"));
        Scene viewScene = new Scene(viewParent);

        Stage srcWin = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        srcWin.setTitle("Database Search");
        srcWin.setScene(viewScene);
        srcWin.centerOnScreen();
        srcWin.show();
    }

    public void setTheController(String email, DoubleLinkedListCircle<referenceNumber> inquiredHouses)
    {
        this.email = email;
        this.inquireD = inquiredHouses;
        this.receipt1 = inquiredHouses.getHead();
        this.receipt2 = receipt1.next;
        this.receipt3 = receipt2.next;
        switch(inquiredHouses.getNodeCounter())
        {
            case 1:
            {
                resultField1.setText("You've succesfully inquired for\n" +
                        "House #1.\n" +
                        "\n" +
                        "Please wait for an incoming text \n" +
                        "within the day to schedule your \n" +
                        "visit and tour of the House!\n" +
                        "\n" +
                        "Take note of your Ref. " + receipt1.info.getReferenceNumber());

                        resultField2.setDisable(true);
                        resultField3.setDisable(true);

                        resultField2.setText("");
                        resultField3.setText("");
                        break;
            }
            case 2:
            {
                resultField1.setText("You've succesfully inquired for\n" +
                        "House #1.\n" +
                        "\n" +
                        "Please wait for an incoming text \n" +
                        "within the day to schedule your \n" +
                        "visit and tour of the House!\n" +
                        "\n" +
                        "Take note of your Ref. " + receipt1.info.getReferenceNumber());

                resultField2.setText("You've succesfully inquired for\n" +
                        "House #2.\n" +
                        "\n" +
                        "Please wait for an incoming text \n" +
                        "within the day to schedule your \n" +
                        "visit and tour of the House!\n" +
                        "\n" +
                        "Take note of your Ref. " + receipt2.info.getReferenceNumber());

                resultField3.setDisable(true);
                resultField3.setText("");
                break;

            }
            default:
            {
                resultField1.setText("You've succesfully inquired for\n" +
                        "House #1.\n" +
                        "\n" +
                        "Please wait for an incoming text \n" +
                        "within the day to schedule your \n" +
                        "visit and tour of the House!\n" +
                        "\n" +
                        "Take note of your Ref. " + receipt1.info.getReferenceNumber());

                resultField2.setText("You've succesfully inquired for\n" +
                        "House #2.\n" +
                        "\n" +
                        "Please wait for an incoming text \n" +
                        "within the day to schedule your \n" +
                        "visit and tour of the House!\n" +
                        "\n" +
                        "Take note of your Ref. " + receipt2.info.getReferenceNumber());

                resultField3.setText("You've succesfully inquired for\n" +
                        "House #3.\n" +
                        "\n" +
                        "Please wait for an incoming text \n" +
                        "within the day to schedule your \n" +
                        "visit and tour of the House!\n" +
                        "\n" +
                        "Take note of your Ref. " + receipt3.info.getReferenceNumber());
                break;
            }
        }


    }
}
