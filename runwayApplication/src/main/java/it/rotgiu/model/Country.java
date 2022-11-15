package it.rotgiu.model;

import java.util.List;


public class Country {

	private String code;
	private String name;
	
	
	public static void printObj(Country c) {
		if (c!=null)
			System.out.printf("%-20s %-20s\n", c.getCode(), c.getName());
		
	}
	public static void printObjList(List<Country> list) {
		if (list!=null && !list.isEmpty()) {
			System.out.printf("%-20s %-20s\n", "Code", "Name");
			for(Country c : list)
				printObj(c);
		}	
		else 
			System.out.println("NO result found!");
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Country(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
}
