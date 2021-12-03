package Controllers;

import javaClasses.houseDetails;

public class ResultUiController
{
    private houseDetails[] retrievedHoused;

    public void setHouseDetails(houseDetails[] received)
    {
        this.retrievedHoused = received;
    }
}
