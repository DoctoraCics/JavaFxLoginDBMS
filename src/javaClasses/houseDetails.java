package javaClasses;

public class houseDetails
{
    private int houseId;
    private int floors;
    private int bedrooms;
    private int kitchens;
    private int bathrooms;
    private int pool;
    private int garage;
    private int price;
    private boolean inquireD;
    private String location;

    public houseDetails(int floors, int bedrooms, int kitchens,int bathrooms, int pool, int garage, int price,int houseId, String location)
    {
        this.floors=floors;
        this.bedrooms=bedrooms;
        this.kitchens=kitchens;
        this.bathrooms=bathrooms;
        this.pool=pool;
        this.garage=garage;
        this.price=price;
        this.inquireD = false;
        this.houseId = houseId;
        this.location = location;
    }

    @Override
    public String toString()
    {
        String yesNoPool = "No";
        String yesNoGarage = "No";
        if(this.pool == 1)
        {
            yesNoPool = "Yes";
        }
        if(this.garage == 1)
        {
            yesNoGarage = "Yes";
        }
        String compile =
                "Floors: " + this.floors + "\n" +
                "Bedrooms: " + this.bedrooms + "\n" +
                "Kitchens: " + this.kitchens + "\n" +
                "Bathrooms: " + this.bathrooms + "\n" +
                "Pool: " + yesNoPool + "\n" +
                "Garage: " + yesNoGarage + "\n" +
                "Price: " + this.price + "\n" +
                "Location: " + this.location;
        return compile;
    }

    public int getFloors() {
        return floors;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getKitchens() {
        return kitchens;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public int isPool() {
        return pool;
    }

    public int isGarage() {
        return garage;
    }

    public int getPrice() {
        return price;
    }

    public void setInquireD(boolean inquiry)
    {
        this.inquireD = inquiry;
    }

    public boolean getInquired()
    {
        return this.inquireD;
    }

    public int getHouseId(){return this.houseId;}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
