package it.rotgiu.model;

import java.util.List;

public class Runway {
	private Long id;
	private Long airportId;
	
	
	public static void printObj(Runway r) {
		if (r!=null)
			System.out.printf("%-20s %-20s\n", r.getId(), r.getAirportId());		
	}
	
	public static void printObjList(List<Runway> list) {
		if (list!=null && !list.isEmpty()) {
			System.out.printf("%-20s %-20s\n", "Id", "AirportId");
			for(Runway r : list)
				printObj(r);
		}	
		else 
			System.out.println("NO result found!");
	}
	
	public Runway(Long id, Long airportId) {
		super();
		this.id = id;
		this.airportId = airportId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAirportId() {
		return airportId;
	}
	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}

}
