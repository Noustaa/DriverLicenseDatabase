import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class DatabaseInstance extends Search implements DatabaseManager {

    List<Driver> driversList = new ArrayList<>();
    File database = new File("database");

    DatabaseInstance()
    {
        driversList = getDriverList();
    }

    @Override
    public boolean addDriver(Driver driver)
    {
        try
        {
            if (!database.createNewFile()) {
                driversList = getDriverList();
            }
            driversList.add(driver);
            FileOutputStream fileoutstream = new FileOutputStream(database);
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driversList);
            objoutstream.close();
            fileoutstream.close();
            return true;
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeDriver(int driverIndex)
    {
        try 
        {
            driversList = getDriverList();
            driversList.remove(driverIndex);
            FileOutputStream fileoutstream = new FileOutputStream(database);
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driversList);
            objoutstream.close();
            fileoutstream.close();
            return true;
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Driver> getDriverList()
    {       
        try
        {
            if (database.createNewFile() || Files.size(database.toPath()) < 1) {
                return this.driversList;
            }
            FileInputStream fileinstream = new FileInputStream(database);
            ObjectInputStream objinstream = new ObjectInputStream(fileinstream);

            this.driversList = (List<Driver>) objinstream.readObject();

            objinstream.close();
            fileinstream.close();
            return this.driversList;
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