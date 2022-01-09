import java.io.Serializable;
 
public class Driver implements Serializable  {
 
    String firstName, lastName;
    int age;
    char gender;
    DriverLicense driverLicense;
 
    //Getters and setters
 
    Driver(String firstName, String lastName, int age, char gender, DriverLicense dLicense) {
        this.age = age;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverLicense = dLicense;
    }
 
    @Override
    public String toString() {
        return "Driver [ID=" + driverLicense.id + ", FirstName=" + firstName + ", LastName=" + lastName + ", Age=" + age + ", Gender=" + gender +", Insurance policy="+driverLicense.insuranceType+"]";
    }
}