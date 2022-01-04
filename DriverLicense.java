import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DriverLicense implements Serializable {
    String id;
    Date deliveryDate, expiryDate, suspensionDate;
    Boolean isValid, isSuspended;
    List<Car> cars;
}