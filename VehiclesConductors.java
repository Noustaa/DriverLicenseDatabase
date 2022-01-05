import java.util.*;
import java.io.*;

public class VehiclesConductors
{
    public static void main(String[] args) throws Exception 
    {
        File database = new File("database");
        Deserializer deserializer = new Deserializer();
        DriverLicense driverLicense = new DriverLicense();
        driverLicense.id = "61Y03";
        Driver driver = new Driver("Abraham", "Lincoln", 47, 'M', driverLicense);

        Serializer serializer = new Serializer();
        serializer.AddDriver(driver, database.createNewFile());

        // Deserialize
        List<Driver> driverList = new ArrayList<>();
        driverList = deserializer.Deserialize();
        serializer.RemoveDriver();
        deserializer.ShowList();

    }
}