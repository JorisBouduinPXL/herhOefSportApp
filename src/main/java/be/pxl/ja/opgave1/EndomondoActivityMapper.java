package be.pxl.ja.opgave1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EndomondoActivityMapper implements ActivityMapper {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Override
    public Activity map(String[] data) {
        Activity activity = new Activity();
        activity.setCustomerNumber(data[1]);
        activity.setActivityDate(LocalDate.parse(data[0], FORMAT));
        activity.setActivityType(ActivityType.valueOf(data[2].toUpperCase()));
        activity.setDistance(Double.parseDouble(data[3]));
        activity.setTracker(ActivityTracker.ENDOMONDO);
        return activity;
    }
}
