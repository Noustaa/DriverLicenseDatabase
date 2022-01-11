import java.io.Serializable;
import java.util.*;

public class DriverLicense implements Serializable {
    String id, insuranceType;
    Date deliveryDate, suspensionDate;
    Boolean isSuspended = false;
    List<Car> cars;
}