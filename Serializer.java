import java.io.*;
import java.util.*;

public class Serializer extends Search {
    void AddDriver(Driver driver, Boolean fileCreation)
    {
        try
        {
            // Scanner scan = new Scanner(System.in);
            // System.out.println("Add a new driver/license:");
            // System.out.print("First Name: ");
            // String firstName = scan.next();
            // System.out.print("Last Name: ");
            // String lastName = scan.next();
            // System.out.print("Age: ");
            // int age = scan.nextInt();
            // System.out.print("Gender (M or F): ");
            // char gender = scan.next().toUpperCase().charAt(0);
            List<Driver> driverList = new ArrayList<>();
            Deserializer deserializer = new Deserializer();
            if (!fileCreation) {
                driverList = deserializer.Deserialize();
            }
            driverList.add(driver);
            FileOutputStream fileoutstream = new FileOutputStream("database");
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driverList);
            objoutstream.close();
            fileoutstream.close();
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
        }
    }

    void RemoveDriver()
    {
        try 
        {
            System.out.println("Do you want to delete the driver by name or by license ID ? (1 for name, 2 for ID)");
            Scanner scan = new Scanner(System.in);
            int searchCriteria = scan.nextInt();
            System.out.println("Please enter your search:");
            String searchField = scan.next();
            int driverIndex = SearchDriver(searchField, searchCriteria);
            scan.close();
            List<Driver> driverList = new ArrayList<>();
            Deserializer deserializer = new Deserializer();
            driverList = deserializer.Deserialize();
            driverList.remove(driverIndex);
            FileOutputStream fileoutstream = new FileOutputStream("database");
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driverList);
            objoutstream.close();
            fileoutstream.close();
            System.out.println("The driver has been removed correctly.");
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
        }
    }
}