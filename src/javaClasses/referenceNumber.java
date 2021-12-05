package javaClasses;

public class referenceNumber
{
    public int getReferenceNumber() {
        return referenceNumber;
    }
    private int referenceNumber;
    private int amount;

    public referenceNumber(int receiveAmount, int theNumber)
    {
        this.referenceNumber = theNumber;
        this.amount = receiveAmount;
    }
}
