package be.pxl.ja.opgave1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerRepository {
	private Map<String, Customer> customers = new HashMap<>();


	
	public CustomerRepository() {
		for (Customer customer : Customers.customers) {
			customers.put(customer.getCustomerNumber(), customer);
		}
	}

	// TODO: voorzie hier de implementatie om een klant te zoeken adhv zijn klantnummer
	public Customer getByCustomerNumber(String customerNumber) {
		Customers.customers.stream().filter(customer -> customer.getCustomerNumber().equals(customerNumber)).collect(Collectors.toList());
		return null;
	}
	
	public List<Customer> findAll() {
		Customers.customers.stream().collect(Collectors.toList());
		// TODO: voorzie hier de implementatie om een lijst me alle klanten te retourneren
		return null;
	}
}
