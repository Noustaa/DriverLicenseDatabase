import java.text.SimpleDateFormat;
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
        "8) Add a car\n"+
        "0) Exit");
        System.out.print("\nPlease enter a valid number (0 - 8): ");
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        boolean inputTest = false;
        System.out.println("Add a new driver/license...\n");
        scan.nextLine(); //init
        System.out.print("First Name: ");
        String firstName = scan.nextLine().trim();
        System.out.print("Last Name: ");
        String lastName = scan.nextLine().trim();
        String birthDateString;
        Date birthDate = new Date();
        while (!inputTest)
        {
            try {
                System.out.print("Enter the Birth Date (DD/MM/YYYY): ");
                birthDateString = scan.nextLine().trim();
                birthDate = dateFormat.parse(birthDateString);
                inputTest = true;
            } catch (Exception e) {
                inputTest = false;
            }
        }
        inputTest = false;
        char gender = ' ';
        while (gender != 'M' && gender != 'F')
        {
            System.out.print("Gender (M or F): ");
            gender = scan.nextLine().toUpperCase().trim().charAt(0);
        }
        DriverLicense driverLicense = new DriverLicense();
        System.out.print("License ID: ");
        driverLicense.id = scan.nextLine();
        while (!inputTest) {
            try {
                System.out.print("Please select an insurance policy:\n1) Liability coverage\n2) Collision insurance\n3) Comprehensive insurance\033[3A\033[8C");
                driverLicense.insuranceType = selectInsurancePolicy(scan.nextInt());
                inputTest = true;
            } catch (Exception e) {
                inputTest = false;
                scan.nextLine();
            }
        }
        inputTest = false;
        System.out.println("\033[1A\033[K\033[s\033[1B\033[K\033[1B\033[K\033[1B\033[K\033[1B\033[K\033[uInsurance policy: "+driverLicense.insuranceType);
        String deliveryDate = "";
        scan.nextLine(); //init
        while (!inputTest)
        {
            try {
                System.out.print("Enter the delivery date (DD/MM/YYYY): ");
                deliveryDate = scan.nextLine().trim();
                driverLicense.deliveryDate = dateFormat.parse(deliveryDate);
                inputTest = true;
            } catch (Exception e) {
                inputTest = false;
            }
        }
        inputTest = false;
        Driver driver = new Driver(firstName, lastName, birthDate, gender, driverLicense);
        System.out.print("Do you want to add a car to this driver (Y for yes/N for no)? ");
        if (scan.next().toUpperCase().charAt(0) == 'Y')
        {
            driver.driverLicense.cars = new ArrayList<>();
            driver.driverLicense.cars.add(addCar(databaseInstance, false));
        }
        else 
        {
            driver.driverLicense.cars = new ArrayList<>();
        }
        try {
            databaseInstance.addDriver(driver);
        } catch (DriverAlreadyExistException e) {
            System.out.println("\n\n"+e.getMessage());
            pressEnterToContinue();
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
        int driverIndex = databaseInstance.searchDriver(databaseInstance, scan);
        clearScreen();
        if (driverIndex == -1)
        {
            pressEnterToContinue();
            return;
        }
        String driverInfo = driversList.get(driverIndex).toString();

        try {
            databaseInstance.removeDriver(driverIndex);
        } catch (Exception e) {
            System.out.println("There was an error. Driver has not been removed.");
            pressEnterToContinue();
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

    String selectInsurancePolicy(int choice) throws Exception
    {
        switch (choice) {
            case 1:
                return "Liability coverage";
            case 2:
                return "Collision insurance";
            case 3:
                return "Comprehensive insurance";
            default:
                throw new Exception("Wrong value.");
        }
    }

    Car addCar(DatabaseInstance databaseInstance, boolean withSearch)
    {
        Car car = new Car();
        if (withSearch)
        {
            clearScreen();
            int driverIndex = databaseInstance.searchDriver(databaseInstance, scan);
            clearScreen();
            System.out.print("Please enter the car Numberplate: ");
            scan.nextLine();
            car.numberplate = scan.nextLine();
            databaseInstance.driversList.get(driverIndex).driverLicense.cars.add(car);
            databaseInstance.updateDatabase();
            System.out.println("\033[3BThe car \""+car+"\" has been correctly added.");
            pressEnterToContinue();
            return car;
        }
        System.out.print("Please enter the car Numberplate: ");
        scan.nextLine();
        car.numberplate = scan.nextLine();
        return car;
    }

    void removeCar(DatabaseInstance databaseInstance)
    {
        clearScreen();
        int driverIndex = databaseInstance.searchDriver(databaseInstance, scan);
        clearScreen();
        int carIndex = databaseInstance.searchCar(driverIndex, databaseInstance, scan);
        System.out.println("\033[3BThe car \""+databaseInstance.driversList.get(driverIndex).driverLicense.cars.get(carIndex)+"\" has been correctly removed.");
        databaseInstance.driversList.get(driverIndex).driverLicense.cars.remove(carIndex);
        databaseInstance.updateDatabase();
        pressEnterToContinue();
        return;
    }

    void suspendLicense(DatabaseInstance databaseInstance)
    {
        int driverIndex = databaseInstance.searchDriver(databaseInstance, scan);
        clearScreen();
        System.out.print("Do you want to suspend this license (Y for yes/N for no) ? \033[s \n"+databaseInstance.driversList.get(driverIndex)+"\033[u");
        if (scan.next().charAt(0) == 'Y')
        {
            databaseInstance.driversList.get(driverIndex).driverLicense.isSuspended = true;
            boolean formatTest = false;
            scan.nextLine(); //init
            while (!formatTest)
            {
                try {
                    clearScreen();
                    System.out.print("Enter the suspension end date (DD/MM/YYYY): ");
                    String suspensionDate = scan.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    databaseInstance.driversList.get(driverIndex).driverLicense.suspensionDate = dateFormat.parse(suspensionDate);
                    formatTest = true;
                } catch (Exception e) {
                    formatTest = false;
                }
            }
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
        int driverIndex = databaseInstance.searchDriver(databaseInstance, scan);
        clearScreen();
        System.out.print("Do you want to renew this license (Y for yes/N for no) ? \033[s \n"+databaseInstance.driversList.get(driverIndex)+"\033[u");
        if (scan.next().charAt(0) == 'Y')
        {
            databaseInstance.driversList.get(driverIndex).driverLicense.isSuspended = false;
            databaseInstance.updateDatabase();
            clearScreen();
            System.out.println("This licence has been renewed correctly:\n"+databaseInstance.driversList.get(driverIndex));
            pressEnterToContinue();
            return;
        }
        else
        {
            System.out.println("\033[3B");
            return;
        }
    }

    void changeInsurancePolicy(DatabaseInstance databaseInstance)
    {
        clearScreen();
        int driverIndex = databaseInstance.searchDriver(databaseInstance, scan);
        clearScreen();
        boolean inputTest = false;
        while (!inputTest) {
            try {
                System.out.print("Please select the new insurance policy: \033[s\n1) Liability coverage\n2) Collision insurance\n3) Comprehensive insurance\033[u");
                databaseInstance.driversList.get(driverIndex).driverLicense.insuranceType = selectInsurancePolicy(scan.nextInt());
                inputTest = true;
            } catch (Exception e) {
                inputTest = false;
                scan.nextLine();
            }
        }
        clearScreen();
        System.out.println("The insurance policy has correctly been changed to: "+databaseInstance.driversList.get(driverIndex).driverLicense.insuranceType);
        pressEnterToContinue();
        return;
    }
}