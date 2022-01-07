import java.util.List;

public interface DatabaseManager {
    boolean addDriver(Driver driver);
    boolean removeDriver(int driverIndex);
    List<Driver> getDriverList();
}