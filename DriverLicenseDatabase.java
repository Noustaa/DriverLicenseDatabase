import java.util.*;

public class DriverLicenseDatabase
{
    private void showList() 
    {
        List<Driver> driverList = new ArrayList<>();
        DatabaseInstance databaseInstance = new DatabaseInstance();
        driverList = databaseInstance.getDriverList();
        for (Driver drivers : driverList) {
            System.out.println(drivers);
        }
    }
    public static void main(String[] args) throws Exception 
    {
        DriverLicenseDatabase driverLicenseDatabase = new DriverLicenseDatabase();
        DatabaseInstance databaseInstance = new DatabaseInstance();

        databaseInstance.addDriver();
        databaseInstance.removeDriver();
        driverLicenseDatabase.showList();
    }
}