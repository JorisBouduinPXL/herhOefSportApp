package be.pxl.ja.opgave1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityProcessor {

	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static final DateTimeFormatter STRAVAFORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private CustomerRepository customerRepository;
	
	public ActivityProcessor(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<Activity> processActivities(Path activityFile, Path errorFile) {
		// TODO: implement this method
		List<Activity> activities = new ArrayList<>();
		return activities;
	}



	public List<Activity> activityReader(Path path){
		List<Activity> list = new ArrayList<>();
		try(BufferedReader reader = Files.newBufferedReader(path)){
			reader.readLine();
			String line = reader.readLine();
			while (line != null){
				if (path.toString().toUpperCase(Locale.ROOT).contains("ENDOMONDO")){
					list.add(mapLineToEndomondoActivity(line));
					line = reader.readLine();
				}
				if (path.toString().toUpperCase(Locale.ROOT).contains("STRAVA")){
					list.add(mapLineToSravaActivity(line));
					line = reader.readLine();
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		// TODO: implement this method
		return list;
	}

	private Activity mapLineToSravaActivity(String line) {
		Activity activity = new Activity();
		String[] parts = line.split("[ ;]");
		activity.setCustomerNumber(parts[2]);
		activity.setActivityType(ActivityType.valueOf(parts[4].toUpperCase(Locale.ROOT)));
		activity.setActivityDate(LocalDate.parse(parts[3], STRAVAFORMAT));
		activity.setDistance(Double.parseDouble(parts[5]));
		return activity;
	}

	//
	public static Activity mapLineToEndomondoActivity(String line){
		Activity activity = new Activity();
		String[] parts = line.split(";");
		activity.setActivityDate(LocalDate.parse(parts[0], FORMAT));
		activity.setCustomerNumber(parts[1]);
		activity.setActivityType(ActivityType.valueOf(parts[2].toUpperCase(Locale.ROOT)));
		activity.setDistance(Double.parseDouble(parts[3]));
		return activity;
	}
}
