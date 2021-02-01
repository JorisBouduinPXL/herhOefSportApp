package be.pxl.ja.opgave1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer implements Comparable<Customer>{
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String customerNumber;
	private String name;
	private String firstName;
	private String city;
	private LocalDate dateOfBirth;
	private int points;

	public Customer(String customerNumber, String firstName, String lastName, LocalDate dateOfBirth, String city) {
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.name = lastName;
		this.dateOfBirth = dateOfBirth;
		this.city = city;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return firstName + " " + name + " " + FORMAT.format(dateOfBirth) + " " + points;
	}

	@Override
	public int compareTo(Customer otherCustomer) {
		// TODO Auto-generated method stub
		return otherCustomer.points - points;
	}
}
