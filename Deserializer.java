import java.util.*;
import java.io.*;

public class Deserializer {

    List<Driver> Deserialize()
    {       
        try
        {
            FileInputStream fileinstream = new FileInputStream("database");
            ObjectInputStream objinstream = new ObjectInputStream(fileinstream);
 
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

    void ShowList(List<Driver> driverList) {
        for (Driver drivers : driverList) {
            System.out.println(drivers);
        }
    }
}