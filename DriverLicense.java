import java.io.Serializable;
import java.util.List;

public class DriverLicense implements Serializable {
    String id, insuranceType, deliveryDate, suspensionDate = "None";
    Boolean isSuspended = false;
    List<Car> cars;
}