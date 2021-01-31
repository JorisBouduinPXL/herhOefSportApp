package be.pxl.ja.opgave1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Opgave1 {
	public static void main(String[] args) {
		CustomerRepository customerRepository = new CustomerRepository();
		customerRepository.findAll();
		System.out.println("*** Klanten uit Louisville:");
		// TODO: 1
		Path srcDir = Path.of(System.getProperty("user.dir"),"src");
		Path activityFile = srcDir.resolve("main/resources/opgave1/activities_from_strava.txt");
		Path errorFile = Paths.get("target/errorfile");
		ActivityProcessor activityProcessor = new ActivityProcessor(customerRepository);
		activityProcessor.processActivities(activityFile,errorFile);
		
		System.out.println("*** Jarige klanten: ");
		// TODO: 2
		
		System.out.println("*** 10 jongste klanten:");
		// TODO: 3
	
		ActivityProcessor activityFileProcessor = new ActivityProcessor(customerRepository);
		List<Activity> allActivities = new ArrayList<>();
		// TODO: 4
		

		System.out.println("*** Top 10 klanten");
		// TODO: 5		
		
		System.out.println("** Alle activiteiten meest actieve klant (gesorteerd op datum):");
		// TODO: 6
	
	}
}
