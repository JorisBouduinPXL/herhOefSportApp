package be.pxl.ja.opgave1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerRepository {
	private Map<String, Customer> customers = new HashMap<>();


	
	public CustomerRepository() {
		for (Customer customer : Customers.customers) {
			customers.put(customer.getCustomerNumber(), customer);
		}
	}

	// TODO: voorzie hier de implementatie om een klant te zoeken adhv zijn klantnummer
	public Customer getByCustomerNumber(String customerNumber) {
		return customers.get(customerNumber);
//		for (Customer customer : Customers.customers){
//			if (customer.getCustomerNumber().equals(customerNumber)){
//				return customer;
//			}
//		}
//		return null;
	}
	
	public List<Customer> findAll() {
		return new ArrayList<>(customers.values());
		// TODO: voorzie hier de implementatie om een lijst me alle klanten te retourneren
	}
}
