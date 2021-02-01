package be.pxl.ja.opgave1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
		List<Activity> activities = activityReader(activityFile);

		return activities;
	}


	// implementatie voorbeeldexamen
	public List<Activity> readActivities(Path activityFile, Path errorFile){
		List<Activity> activities = new ArrayList<>();
		ActivityTracker tracker = ActivityTracker.STRAVA;
		//File controleren en indien verkeerd wegschrijven naar errorfile
		if (activityFile.toString().contains("endomondo")){
			tracker = ActivityTracker.ENDOMONDO;
		}else if (activityFile.toString().contains("strave")){
			tracker = ActivityTracker.STRAVA;
		}else {
			writeErrorMessage(errorFile,activityFile.getFileName().toString(), "INVALLID FILENAME");
			return activities;
		}
		// controleren of errorfile bestaat en aanmaken indien het nog niet bestaat
		if (!Files.exists(errorFile)){
			try{
				Files.createDirectories(errorFile.getParent());
				Files.createFile(errorFile);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		String line = null;
		try	(BufferedReader reader = Files.newBufferedReader(activityFile);
				BufferedWriter writer = Files.newBufferedWriter(errorFile,StandardOpenOption.APPEND)){
			while ((line = reader.readLine()) != null){
				String[] data = line.split(";");
				if (data.length != 4){
					writeErrorMessage(errorFile,activityFile.getFileName().toString(),"INVALLID LINE: " + line);
				}
				try {
					Activity activity = tracker.map(data);
					Customer customer = customerRepository.getByCustomerNumber(activity.getCustomerNumber());
					if (customer == null){
						writeErrorMessage(errorFile,activityFile.getFileName().toString(),"UNKNOWN CUSTOMER: " + line);
					}else {
						activities.add(activity);
//						customer.addPoints(activity.getPoints);
					}
				}catch (Exception e){
					writeErrorMessage(errorFile,activityFile.getFileName().toString(),e.getMessage());
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		return activities;
	}



	private void writeErrorMessage(Path errorFile, String fileName, String description) {
		StringBuilder errorMassage = new StringBuilder();
		errorMassage.append(LocalDateTime.now()).append(" - ").append(fileName).append(" - ");
		errorMassage.append(description).append("\n");
		try{
			Files.write(errorFile, errorMassage.toString().getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e){
			e.printStackTrace();
		}
	}


	public List<Activity> activityReader(Path path){
		List<Activity> activities = new ArrayList<>();
		try(BufferedReader reader = Files.newBufferedReader(path)){
			reader.readLine();
			String line = reader.readLine();
			while (line != null){
				if (path.toString().toUpperCase().contains("ENDOMONDO")){
					activities.add(mapLineToEndomondoActivity(line));
					line = reader.readLine();
				}
				if (path.toString().toUpperCase().contains("STRAVA")){
					activities.add(mapLineToSravaActivity(line));
					line = reader.readLine();
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		// TODO: implement this method
		return activities;
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
		activity.setActivityType(ActivityType.valueOf(parts[2].toUpperCase()));
		activity.setDistance(Double.parseDouble(parts[3]));
		activity.setTracker(ActivityTracker.ENDOMONDO);
		return activity;
	}
}
