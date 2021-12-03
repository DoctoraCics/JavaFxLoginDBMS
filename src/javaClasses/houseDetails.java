package javaClasses;

public class houseDetails
{
    private int floors;
    private int bedrooms;
    private int kitchens;
    private int bathrooms;
    private int pool;
    private int garage;
    private int price;

    public houseDetails(int floors, int bedrooms, int kitchens,int bathrooms, int pool, int garage, int price)
    {
        this.floors=floors;
        this.bedrooms=bedrooms;
        this.kitchens=kitchens;
        this.bathrooms=bathrooms;
        this.pool=pool;
        this.garage=garage;
        this.price=price;
    }

    @Override
    public String toString()
    {
        String compile =
                "Floors: " + this.floors + "\n" +
                "Bedrooms: " + this.bedrooms + "\n" +
                "Kitchens: " + this.kitchens + "\n" +
                "Bathrooms: " + this.bathrooms + "\n" +
                "Pool: " + this.pool + "\n" +
                "Garage: " + this.garage + "\n" +
                "Price: " + this.price + "\n";
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
}
