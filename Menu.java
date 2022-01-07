import java.util.*;

public class Menu{
    Scanner scan = new Scanner(System.in);

    int showMainMenu()
    {
        clearScreen();
        System.out.println(
        "What do you want to do ?\n"+
        "1) Add a new driver/license\n"+
        "2) Remove a driver/license\n"+
        "3) Change a driver insurance policy\n"+
        "4) Show the complete drivers list\n"+
        "0) Exit");
        System.out.print("\nPlease enter a valid number (0 - 4): ");
        try {
            int choice = scan.nextInt();
            if (choice == 0)
            {
                scan.close();
            }
            return choice;
        } catch (Exception e) {
            scan.next(); //clear scanner input
            return -1;
        }
    }

    void showDriversList(List<Driver> driversList) 
    {
        clearScreen();
        if (driversList.size() < 1)
        {
            System.out.println("The database is empty.");
            pressEnterToContinue();
            return;
        }
        for (Driver drivers : driversList) {
            System.out.println(drivers);
        }
        pressEnterToContinue();
    }

    void showCreateDriver(DatabaseInstance databaseInstance)
    {
        clearScreen();
        System.out.println("Add a new driver/license...\n");
        System.out.print("First Name: ");
        String firstName = scan.next();
        System.out.print("Last Name: ");
        String lastName = scan.next();
        System.out.print("Age: ");
        int age = scan.nextInt();
        System.out.print("Gender (M or F): ");
        char gender = scan.next().toUpperCase().charAt(0);
        System.out.print("License ID: ");
        String driverLicenseId = scan.next();
        DriverLicense driverLicense = new DriverLicense();
        driverLicense.id = driverLicenseId;
        Driver driver = new Driver(firstName, lastName, age, gender, driverLicense);
        if (!databaseInstance.addDriver(driver)) {
            System.out.println("There was an error. Driver creation cancelled.");
            return;
        }
        System.out.println("\n"+driver+"\n\nDriver has been created successfully.");
        pressEnterToContinue();
    }

    void showRemoveDriver(List<Driver> driversList, DatabaseInstance databaseInstance)
    {
        clearScreen();
        if (driversList.size() < 1)
        {
            System.out.println("The database is empty, there is nothing to remove.");
            pressEnterToContinue();
            return;
        }
        System.out.println("Do you want to delete the driver by name or by license ID ? (1 for name, 2 for ID)");
        int searchCriteria = scan.nextInt();
        System.out.println("Please enter your search:");
        String searchField = scan.next();
        int driverIndex = databaseInstance.searchDriver(searchField, searchCriteria, driversList, databaseInstance);
        if (driverIndex == -1)
        {
            pressEnterToContinue();
            return;
        }
        String driverInfo = driversList.get(driverIndex).toString();
        if (!databaseInstance.removeDriver(driverIndex))
        {
            System.out.println("There was an error. Driver has not been removed.");
            return;
        }
        System.out.println("\n"+driverInfo+"\n\nThe driver has been removed correctly.");
        pressEnterToContinue();
    }

    void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void pressEnterToContinue()
    {
        try {
            System.out.print("\nPress ENTER to continue...");
            System.in.read();
        } catch (Exception e) {
            return;
        }
    }
}