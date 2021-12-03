package javaClasses;

public class userData
{
    private String firstName;
    private String lastName;
    private String birthDate;
    private int contactNo;
    private String emailAddress;
    private String home_Address;
    private String passWord;


    public userData(String fname, String lname, String bDay, int contact,
                    String eAddress, String home_Address, String passWord)
    {
        this.firstName = fname;
        this.lastName = lname;
        this.birthDate = bDay;
        this.contactNo = contact;
        this.emailAddress = eAddress;
        this.home_Address = home_Address;
        this.passWord = passWord;
    }
    public String getCompiled()
    {
        String compiled = "";
        compiled += firstName + " " + lastName + " " + birthDate + " " + contactNo + " " + emailAddress + " " + home_Address;
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

    public String gethome_Address(){return home_Address;}

    public String getPassWord(){return passWord;}

}
