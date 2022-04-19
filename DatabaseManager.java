import java.util.List;

public interface DatabaseManager {
    void addDriver(Driver driver) throws DriverAlreadyExistsException, LicenseIdAlreadyExistsException;
    void removeDriver(int driverIndex);
    List<Driver> getDriverList();
    public void updateDatabase();
}