package be.pxl.ja.opgave1;

import java.io.IOException;
import java.nio.file.Files;
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
		ActivityProcessor activityFileProcessor = new ActivityProcessor(customerRepository);
		List<Activity> allActivities = new ArrayList<>();
		activityFileProcessor.processActivities(activityFile,errorFile);

		try {
			for (Path file : Files.newDirectoryStream(Paths.get("resources/opgave1"))) {
				if (!Files.isDirectory(file)) {
					allActivities.addAll(
							activityFileProcessor.readActivities(file, Paths.get("resources/opgave1/log/errors.log")));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("*** Jarige klanten: ");
		// TODO: 2
		
		System.out.println("*** 10 jongste klanten:");
		// TODO: 3
	

		// TODO: 4
		

		System.out.println("*** Top 10 klanten");
		// TODO: 5		
		
		System.out.println("** Alle activiteiten meest actieve klant (gesorteerd op datum):");
		customerRepository.findAll().stream().sorted().limit(10).forEach(System.out::println);

		Customer mostActiveCustomer = customerRepository.findAll().stream().sorted().findFirst().get();

		allActivities.stream().filter(a -> a.getCustomerNumber().equals(mostActiveCustomer.getCustomerNumber())).sorted().forEach(a -> System.out.println(
				a.getActivityDate() + " " + a.getActivityType() + " " + a.getDistance() + " " + a.getPoints()));
		// TODO: 6
	
	}
}
