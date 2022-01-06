import java.io.*;
import java.util.*;

public class DatabaseInstance extends Search implements DatabaseManager {

    List<Driver> driversList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    File database = new File("database");

    @Override
    public void addDriver()
    {
        try
        {
            System.out.println("Add a new driver/license:");
            System.out.print("First Name: ");
            String firstName = scan.next();
            System.out.print("Last Name: ");
            String lastName = scan.next();
            System.out.print("Age: ");
            int age = scan.nextInt();
            System.out.print("Gender (M or F): ");
            char gender = scan.next().toUpperCase().charAt(0);
            System.out.print("License ID: ");
            String driverLicenseId = scan.next();
            DriverLicense driverLicense = new DriverLicense();
            driverLicense.id = driverLicenseId;
            Driver driver = new Driver(firstName, lastName, age, gender, driverLicense);
            if (!database.createNewFile()) {
                driversList = getDriverList();
            }
            driversList.add(driver);
            FileOutputStream fileoutstream = new FileOutputStream("database");
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driversList);
            objoutstream.close();
            fileoutstream.close();
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
        }
    }

    @Override
    public void removeDriver()
    {
        try 
        {
            System.out.println("Do you want to delete the driver by name or by license ID ? (1 for name, 2 for ID)");
            int searchCriteria = scan.nextInt();
            System.out.println("Please enter your search:");
            String searchField = scan.next();
            int driverIndex = searchDriver(searchField, searchCriteria, driversList, this);
            scan.close();
            driversList = getDriverList();
            driversList.remove(driverIndex);
            FileOutputStream fileoutstream = new FileOutputStream("database");
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driversList);
            objoutstream.close();
            fileoutstream.close();
            System.out.println("The driver has been removed correctly.");
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
        }
    }

    @Override
    public List<Driver> getDriverList()
    {       
        try
        {
            FileInputStream fileinstream = new FileInputStream("database");
            ObjectInputStream objinstream = new ObjectInputStream(fileinstream);
 
            @SuppressWarnings("unchecked")
            List<Driver> driverList = (List<Driver>) objinstream.readObject();

            objinstream.close();
            fileinstream.close();
            return driverList;
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
            return null;
        } 
        catch (ClassNotFoundException excep) 
        {
            System.out.println("Class not found");
            excep.printStackTrace();
            return null;
        }
    }
}