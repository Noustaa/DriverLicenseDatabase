import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DriverLicense implements Serializable {
    String id, insuranceType;
    Date deliveryDate, expiryDate, suspensionDate;
    Boolean isValid, isSuspended;
    List<Car> cars;
}