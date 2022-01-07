public class DriverLicenseDatabase {
    public static void main(String[] args) throws Exception {
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
                    //TO DO
                    break;
                case 4:
                    menu.showDriversList(databaseInstance.driversList);
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }       
    }
}