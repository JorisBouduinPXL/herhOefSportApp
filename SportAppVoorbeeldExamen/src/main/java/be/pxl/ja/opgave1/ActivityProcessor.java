package be.pxl.ja.opgave1;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ActivityProcessor {

	private CustomerRepository customerRepository;
	
	public ActivityProcessor(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<Activity> processActivities(Path activityFile, Path errorFile) {
		List<Activity> activities = new ArrayList<>();
//		activities.read
		// TODO: implement this method
		return null;
	}
}
