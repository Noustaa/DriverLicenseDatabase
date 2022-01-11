public class DriverLicenseDatabase {
    public static void main(String[] args){
        Menu menu = new Menu();
        DatabaseInstance databaseInstance = new DatabaseInstance();
    
        while(true)
        {
            int choice = menu.showMainMenu();
            switch (choice) {
                case 1:
                    menu.showCreateDriver(databaseInstance);
                    break;
                case 2:
                    menu.showRemoveDriver(databaseInstance.driversList, databaseInstance);
                    break;
                case 3:
                    menu.changeInsurancePolicy(databaseInstance);
                    break;
                case 4:
                    menu.showDriversList(databaseInstance.driversList);
                    break;
                case 5:
                    menu.suspendLicense(databaseInstance);
                    break;
                case 6:
                    menu.renewLicense(databaseInstance);
                    break;
                case 7:
                    menu.removeCar(databaseInstance);
                    break;
                case 8:
                    menu.addCar(databaseInstance, true);
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }       
    }
}