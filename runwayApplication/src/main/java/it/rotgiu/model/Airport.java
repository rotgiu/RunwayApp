package it.rotgiu.model;

import java.util.List;


public class Airport {

	private String countryCode;
	private Long id;
	private String name;
	
	public static void printObj(Airport a) {
		if (a!=null)
			System.out.printf("%-20s %-20s %-20s\n", a.getId(), a.getCountryCode(), a.getName());	
	}
	public static void printObjList(List<Airport> list) {
		if (list!=null && !list.isEmpty()) {
			System.out.printf("%-20s %-20s %-20s\n", "Id", "CountryCode", "Name");
			for(Airport a : list)
				printObj(a);
		}
		else 
			System.out.println("NO result found!");
	}
	
	public Airport(String countryCode, Long id, String name) {
		super();
		this.countryCode = countryCode;
		this.id = id;
		this.name = name;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
