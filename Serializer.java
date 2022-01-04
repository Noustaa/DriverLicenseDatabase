import java.io.*;
import java.util.*;

public class Serializer extends Throwable{
    void AddSerialize(List<Driver> driver)
    {
        try
        {
            FileOutputStream fileoutstream = new FileOutputStream("database");
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driver);
            objoutstream.close();
            fileoutstream.close();
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
        }
    }
}