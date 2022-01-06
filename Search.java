import java.util.*;

public abstract class Search extends Throwable {

    int searchDriver(String searchField, int searchCriteria, List<Driver> driverList, DatabaseInstance databaseInstance)
    {
        driverList = databaseInstance.getDriverList();
        switch (searchCriteria) {
            case 1:
            for (Driver drivers : driverList) {
                if (searchField.toLowerCase().equals(drivers.lastName.toLowerCase()))
                {
                    drivers.toString();
                    System.out.println("Driver index is "+driverList.indexOf(drivers));
                    return driverList.indexOf(drivers);
                }
            }
            case 2:
            for (Driver drivers : driverList) {
                if (searchField.toLowerCase().equals(drivers.driverLicense.id.toLowerCase()))
                {
                    drivers.toString();
                    System.out.println("Driver index is "+driverList.indexOf(drivers));
                    return driverList.indexOf(drivers);
                }
            }
            default:
            break;
        }
        System.out.println("No driver found.");
        return -1;
    }
}