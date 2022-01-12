import java.util.Scanner;

public abstract class Search extends Throwable {

    int searchDriver(DatabaseInstance databaseInstance, Scanner scan)
    {
        boolean inputTest = false;
        int searchCriteria = 0;
        while (!inputTest)
        {
            try {
                System.out.println("Do you want to select the driver by name or by license ID ? (1 for name, 2 for ID, 3 to select from the drivers list)");
                searchCriteria = scan.nextInt();
                if (searchCriteria<1 || searchCriteria>3) {throw new Exception("Wrong number.");}
                inputTest = true;
            } catch (Exception e) {
                inputTest = false;
                scan.nextLine();
            }
        }
        inputTest = false;
        String searchField = "";
        if (searchCriteria != 3) 
        {
                System.out.println("Please enter your search:");
                searchField = scan.next();
        }
        switch (searchCriteria) {
            case 1:
                for (Driver drivers : databaseInstance.driversList) {
                if (searchField.toLowerCase().equals(drivers.lastName.toLowerCase()))
                {
                    drivers.toString();
                    return databaseInstance.driversList.indexOf(drivers);
                }
            }
            case 2:
            for (Driver drivers : databaseInstance.driversList) {
                if (searchField.toLowerCase().equals(drivers.driverLicense.id.toLowerCase()))
                {
                    drivers.toString();
                    return databaseInstance.driversList.indexOf(drivers);
                }
            }
            case 3:
                System.out.println("Please select a driver: \033[s");
                int i = 1;
                for (Driver drivers : databaseInstance.driversList) {
                    System.out.println(i+") "+drivers);
                    i++;
                }
                int choice = -1;
                while (!inputTest)
                {
                        try {
                            System.out.print("\033[u \033[1D\033[K");
                            choice = scan.nextInt() - 1;
                            inputTest = true;
                        } catch (Exception e) {
                            inputTest = false;
                            scan.nextLine();
                        }
                }
                inputTest = false;
                return (choice);
            default:
            break;
        }
        System.out.println("\nDriver not found.");
        return -1;
    }

    int searchCar(int driverIndex, DatabaseInstance databaseInstance, Scanner scan)
    {
        System.out.print("Select the car you want to remove: \033[s \n");
        int i=1;
        for (Car car : databaseInstance.driversList.get(driverIndex).driverLicense.cars) {
            System.out.println(i+") "+car);
            i++;
        }
        System.out.print("\033[u");
        return (scan.nextInt()-1);
    }
}