package javaClasses;

public class groupQuery
{

    private int floorRange;
    private int floorRange2;

    private int bedRoomRange;
    private int bedRoomRange2;

    private int kitchenNo;

    private int bathroomNo;

    private int garage;

    private int pool;

    private int priceRange;
    private int priceRange2;

    public groupQuery(){}
    public groupQuery(int floor1, int floor2, int bed1, int bed2, int kitchen, int bathroom, int garage, int pool, int price1, int price2)
    {
        this.floorRange = floor1;
        this.floorRange2 = floor2;

        this.bedRoomRange = bed1;
        this.bedRoomRange2 = bed2;

        this.kitchenNo = kitchen;
        this.bathroomNo = bathroom;

        this.garage = garage;
        this.pool = pool;

        this.priceRange = price1;
        this.priceRange2 = price2;
    }

    @Override
    public String toString()
    {
        String compile =
                "Floor1: " + this.floorRange + "\n" +
                "Floor2: " + this.floorRange2 + "\n" +

                "Bedroom1: " + this.bedRoomRange + "\n" +
                "Bedroom2: " + this.bedRoomRange2 + "\n" +

                "Kitchen No: " + this.kitchenNo + "\n" +
                "Bathroom No: " + this.bathroomNo + "\n" +

                "Garage: " + this.garage + "\n" +
                "Pool: " + this.pool + "\n" +

                "Price1: " + this.priceRange + "\n" +
                "Price2: " + this.priceRange2 + "\n";
        return compile;
    }

    public int getFloorRange() {
        return floorRange;
    }

    public void setFloorRange(int floorRange) {
        this.floorRange = floorRange;
    }

    public int getFloorRange2() {
        return floorRange2;
    }

    public void setFloorRange2(int floorRange2) {
        this.floorRange2 = floorRange2;
    }

    public int getBedRoomRange() {
        return bedRoomRange;
    }

    public void setBedRoomRange(int bedRoomRange) {
        this.bedRoomRange = bedRoomRange;
    }

    public int getBedRoomRange2() {
        return bedRoomRange2;
    }

    public void setBedRoomRange2(int bedRoomRange2) {
        this.bedRoomRange2 = bedRoomRange2;
    }

    public int getKitchenNo() {
        return kitchenNo;
    }

    public void setKitchenNo(int kitchenNo) {
        this.kitchenNo = kitchenNo;
    }

    public int getBathroomNo() {
        return bathroomNo;
    }

    public void setBathroomNo(int bathroomNo) {
        this.bathroomNo = bathroomNo;
    }

    public int isGarage() {
        return garage;
    }

    public void setGarage(int garage) {
        this.garage = garage;
    }

    public int isPool() {
        return pool;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public int getPriceRange2() {
        return priceRange2;
    }

    public void setPriceRange2(int priceRange2) {
        this.priceRange2 = priceRange2;
    }

}
