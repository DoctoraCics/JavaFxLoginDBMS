package javaClasses;

public class userData
{
    private String firstName;
    private String lastName;
    private String birthDate;
    private int contactNo;
    private String emailAddress;
    private int houseNo;
    private String streeT;
    private String citY;
    private String barangaY;
    private String provincE;
    private String regioN;
    private int postalCode;


    public userData(String fname, String lname, String bDay, int contact,
                    String eAddress, int housenumber, String street, String city,
                    String barangay, String province, String region, int postal)
    {
        this.firstName = fname;
        this.lastName = lname;
        this.birthDate = bDay;
        this.contactNo = contact;
        this.emailAddress = eAddress;
        this.houseNo = housenumber;
        this.streeT = street;
        this.citY = city;
        this.barangaY = barangay;
        this.provincE = province;
        this.regioN = region;
        this.postalCode = postal;
    }
    public String getCompiled()
    {
        String compiled = "";
        compiled += firstName + " " + lastName + " " + birthDate + " " + contactNo + " " + emailAddress + " " + houseNo +
                " " + streeT + " " + citY + " " + barangaY + " " + provincE + " " + regioN + " " + postalCode;
        return compiled;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public String getStreeT() {
        return streeT;
    }

    public String getCitY() {
        return citY;
    }

    public String getBarangaY() {
        return barangaY;
    }

    public String getProvincE() {
        return provincE;
    }

    public String getRegioN() {
        return regioN;
    }

    public int getPostalCode() {
        return postalCode;
    }

}
