package be.pxl.ja.opgave1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StravaActivityMapper implements ActivityMapper {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @Override
    public Activity map(String[] data) {
        Activity activity = new Activity();
        int lastSpace = data[0].lastIndexOf(" ");
        activity.setCustomerNumber(data[0].substring(lastSpace + 1, data[0].length()));
        activity.setActivityDate(LocalDate.parse(data[1], FORMAT));
        activity.setActivityType(ActivityType.valueOf(data[2].toUpperCase()));
        activity.setDistance(Double.parseDouble(data[3]));
        activity.setTracker(ActivityTracker.STRAVA);
        return activity;
    }
}
