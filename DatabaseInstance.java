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
    public void addDriver(Driver driver) throws DriverAlreadyExistsException, LicenseIdAlreadyExistsException
    {
        try
        {
            if (!database.createNewFile()) {
                driversList = getDriverList();
            }
            for (Driver drivers : driversList) {
                    if (drivers.firstName.equalsIgnoreCase(driver.firstName) && 
                    drivers.lastName.equalsIgnoreCase(driver.lastName) && 
                    drivers.birthDate.equals(driver.birthDate) && 
                    (drivers.gender == driver.gender))
                    {
                        throw new DriverAlreadyExistsException("Driver already exists. Creation has been cancelled.");
                    }
                    if (drivers.driverLicense.id.equalsIgnoreCase(driver.driverLicense.id))
                    {
                        throw new LicenseIdAlreadyExistsException("This licence ID already exists. Creation has been cancelled.");
                    }
            }

            driversList.add(driver);
            FileOutputStream fileoutstream = new FileOutputStream(database);
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(driversList);
            objoutstream.close();
            fileoutstream.close();
            return;
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
            return;
        }
    }

    @Override
    public void removeDriver(int driverIndex)
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
            return;
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
            return;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Driver> getDriverList()
    {       
        try
        {
            if (database.createNewFile() || Files.size(database.toPath()) < 100) {
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

    @Override
    public void updateDatabase() {
        try {
            FileOutputStream fileoutstream = new FileOutputStream(database);
            ObjectOutputStream objoutstream = new ObjectOutputStream(fileoutstream);
            objoutstream.writeObject(this.driversList);
            objoutstream.close();
            fileoutstream.close();
        } 
        catch (IOException excep) 
        {
            excep.printStackTrace();
            return;
        }
    }

}