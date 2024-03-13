package model;

public class Customer {
	private String name;
	private String address;
	private String zipCode;
	private String city;
	private String phoneNumber;
	private String type;
	
	public Customer(String name, String address, String zipCode, String city, String phoneNumber, String type) {
		this.setName(name);
		this.setAddress(address);
		this.setZipCode(zipCode);
		this.setCity(city);
		this.setPhoneNumber(phoneNumber);
		this.setType(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
