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
	private CustomerRepository customerRepository;
	
	public ActivityProcessor(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<Activity> processActivities(Path activityFile, Path errorFile) {
		// TODO: implement this method
		activityReader(activityFile);
		return null;
	}

	public List<Activity> activityReader(Path path){
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = null;
			while ((line = reader.readLine()) != null){
				System.out.println(line);
			}
		}catch (IOException e){
			e.printStackTrace();
		}

		// TODO: implement this method
		return null;
	}

	//
	public static Activity mapLineToEndomondoActivity(String line){
		Activity activity = new Activity();
		String[] parts = line.split(";");
		activity.setActivityDate(LocalDate.parse(parts[1], FORMAT));
		activity.setCustomerNumber(parts[1]);
		activity.setActivityType(ActivityType.valueOf(parts[2].toUpperCase(Locale.ROOT)));
		activity.setDistance(Double.parseDouble(parts[3]));
		return activity;
	}
}
