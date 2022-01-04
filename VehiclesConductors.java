import java.util.*;
import java.io.*;

public class VehiclesConductors
{
    public static void main(String[] args) throws Exception 
    {
        File file = new File("database");
        Deserializer deserializer = new Deserializer();
        List<Driver> deserializesList = new ArrayList<>();
        DriverLicense driverLicense = new DriverLicense();
        driverLicense.id = "82F38";
        if (file.createNewFile())
        {
            deserializesList.add(new Driver("Jodddhn", "Kennedy", 39, 'M', driverLicense));
            Serializer serializer = new Serializer();
            serializer.AddSerialize(deserializesList);
        }
        else
        {
            deserializesList = deserializer.Deserialize();
            deserializesList.add(new Driver("Jodddhn", "Kennedy", 39, 'M', driverLicense));
            Serializer serializer = new Serializer();
            serializer.AddSerialize(deserializesList);
        }

        //Deserialize
        deserializer.ShowList(deserializesList);
    }
}