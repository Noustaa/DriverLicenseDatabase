import java.util.*;

public abstract class Search extends Throwable {

    int SearchDriver(String searchField, int searchCriteria)
    {
        List<Driver> driverList = new ArrayList<>();
        Deserializer deserializer = new Deserializer();
        driverList = deserializer.Deserialize();
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
