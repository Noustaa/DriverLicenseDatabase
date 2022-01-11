import java.io.Serializable;
import java.text.SimpleDateFormat;
 
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
        StringBuilder carStatus = new StringBuilder();
        if (this.driverLicense.cars.size()<1)
        {
            carStatus.append("No cars are registered for this driver.");
        }
        else{
            carStatus.append("Cars registered for this driver (Numberplates):\n");
            for (Car car : driverLicense.cars) {
                carStatus.append(car+"\n");
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (!driverLicense.isSuspended) {
            return "Driver [ID=" + driverLicense.id +
            ", FirstName=" + firstName +
            ", LastName=" + lastName +
            ", Age=" + age +
            ", Gender=" + gender +
            ", Insurance policy=" + driverLicense.insuranceType + "]\n" +
            "Delivery date="+dateFormat.format(driverLicense.deliveryDate)+"\n"+
            "This licence is active.\n"+
            carStatus;
        }
        else{
            return "Driver [ID=" + driverLicense.id +
            ", FirstName=" + firstName +
            ", LastName=" + lastName +
            ", Age=" + age +
            ", Gender=" + gender +
            ", Insurance policy=" + driverLicense.insuranceType + "]\n" +
            "Delivery date="+dateFormat.format(driverLicense.deliveryDate)+"\n"+
            "This licence is suspended until: "+dateFormat.format(driverLicense.suspensionDate)+"\n"+
            carStatus;
        }
    }
}