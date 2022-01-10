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
        "5) Suspend a license\n"+
        "6) Renew a license\n"+
        "7) Remove a car\n"+
        "0) Exit");
        System.out.print("\nPlease enter a valid number (0 - 7): ");
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
            System.out.println(drivers+"\n");
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
        DriverLicense driverLicense = new DriverLicense();
        System.out.print("License ID: ");
        driverLicense.id = scan.next();
        System.out.print("Please select an insurance policy:\n1) Liability coverage\n2) Collision insurance\n3) Comprehensive insurance\033[3A\033[8C");
        driverLicense.insuranceType = selectInsurancePolicy(scan.nextInt());
        System.out.println("\033[1A\033[K\033[s\033[1B\033[K\033[1B\033[K\033[1B\033[K\033[1B\033[K\033[uInsurance policy: "+driverLicense.insuranceType);
        System.out.print("Enter the delivery date (DD/MM/YYYY): ");
        driverLicense.deliveryDate = scan.next();
        Driver driver = new Driver(firstName, lastName, age, gender, driverLicense);
        System.out.print("Do you want to add a car to this driver (Y for yes/N for no)? ");
        if (scan.next().charAt(0) == 'Y')
        {
            driver.driverLicense.cars = new ArrayList<>();
            driver.driverLicense.cars.add(addCar());
        }
        if (!databaseInstance.addDriver(driver)) {
            System.out.println("\nThere was an error. Driver creation cancelled.");
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

    String selectInsurancePolicy(int choice)
    {
        switch (choice) {
            case 1:
                return "Liability coverage";
            case 2:
                return "Collision insurance";
            case 3:
                return "Comprehensive insurance";
            default:
                break;
        }
        return "";
    }

    Car addCar()
    {
        Car car = new Car();
        System.out.print("Please enter the car Numberplate: ");
        scan.nextLine();
        car.numberplate = scan.nextLine();
        return car;
    }

    void removeCar(DatabaseInstance databaseInstance)
    {
        System.out.println("Do you want to select the driver by name or by license ID ? (1 for name, 2 for ID)");
        int searchCriteria = scan.nextInt();
        System.out.println("Please enter your search:");
        String searchField = scan.next();
        int driverIndex = databaseInstance.searchDriver(searchField, searchCriteria, databaseInstance.driversList, databaseInstance);
        System.out.print("Do you want to remove this car (Y for yes/N for no) ? \033[s \n"+databaseInstance.driversList.get(driverIndex)+"\033[u");
        if (scan.next().charAt(0) == 'Y')
        {
            databaseInstance.driversList.get(driverIndex).driverLicense.cars.remove(0);
            databaseInstance.updateDatabase();
            return;
        }
        else
        {
            System.out.println("\033[3B");
            return;
        }
    }

    void suspendLicense(DatabaseInstance databaseInstance)
    {
        System.out.println("Do you want to select the driver by name or by license ID ? (1 for name, 2 for ID)");
        int searchCriteria = scan.nextInt();
        System.out.println("Please enter your search:");
        String searchField = scan.next();
        int driverIndex = databaseInstance.searchDriver(searchField, searchCriteria, databaseInstance.driversList, databaseInstance);
        System.out.print("Do you want to suspend this license (Y for yes/N for no) ? \033[s \n"+databaseInstance.driversList.get(driverIndex)+"\033[u");
        if (scan.next().charAt(0) == 'Y')
        {
            databaseInstance.driversList.get(driverIndex).driverLicense.isSuspended = true;
            System.out.println("\033[3BPlease enter the suspension end date (DDMMYYYY) : ");
            databaseInstance.driversList.get(driverIndex).driverLicense.suspensionDate = scan.next();
            databaseInstance.updateDatabase();
            return;
        }
        else
        {
            System.out.println("\033[3B");
            return;
        }
    }

    void renewLicense(DatabaseInstance databaseInstance)
    {
        System.out.println("Do you want to select the driver by name or by license ID ? (1 for name, 2 for ID)");
        int searchCriteria = scan.nextInt();
        System.out.println("Please enter your search:");
        String searchField = scan.next();
        int driverIndex = databaseInstance.searchDriver(searchField, searchCriteria, databaseInstance.driversList, databaseInstance);
        System.out.print("Do you want to renew this license (Y for yes/N for no) ? \033[s \n"+databaseInstance.driversList.get(driverIndex)+"\033[u");
        if (scan.next().charAt(0) == 'Y')
        {
            databaseInstance.driversList.get(driverIndex).driverLicense.isSuspended = false;
            databaseInstance.updateDatabase();
            return;
        }
        else
        {
            System.out.println("\033[3B");
            return;
        }
    }
}