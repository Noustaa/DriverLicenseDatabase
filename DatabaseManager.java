import java.util.List;

public interface DatabaseManager {
    void addDriver();
    void removeDriver();
    List<Driver> getDriverList();
}