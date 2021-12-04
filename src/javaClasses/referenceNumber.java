package javaClasses;

public class referenceNumber
{
    public int getReferenceNumber() {
        return referenceNumber;
    }
    public int getAmount() {
        return amount;
    }
    private int referenceNumber;
    private int amount;

    public referenceNumber(int receiveAmount, int theNumber)
    {
        this.referenceNumber = theNumber;
        this.amount = receiveAmount;
    }
}
