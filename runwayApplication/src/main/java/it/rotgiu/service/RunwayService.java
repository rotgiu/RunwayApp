package it.rotgiu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.rotgiu.model.Airport;
import it.rotgiu.model.Country;
import it.rotgiu.model.Runway;

public class RunwayService {
	
	private List<Airport> airportList;
	private List<Country> countryList;
	private List<Runway> runwayList;
	private List<Country> topTenCountry;
	
	public RunwayService(List<Airport> airportList, List<Country> countryList, List<Runway> runwayList) {
		super();
		this.airportList = airportList;
		this.countryList = countryList;
		this.runwayList = runwayList;
	}


	public List<Runway> retrieveRunwayByCountryCode(String search) {
		List<Long> airportIds = new ArrayList<Long>();
		List<Runway> runways = new ArrayList<Runway>();
		for (Airport a: airportList) {
			if (a.getCountryCode().toUpperCase().equals(search.toUpperCase())) {
				airportIds.add(a.getId());
			}
		}
		if (!airportIds.isEmpty()) {
			for (Runway r : runwayList) {
				if (airportIds.contains(r.getAirportId())) {
					runways.add(r);
				}
			}
		}
		return runways;
	}
	
	public List<Runway> retrieveRunwayByCountryName(String search) {
		String countryCode = "";
		List<Runway> runways = new ArrayList<Runway>();
		boolean found = false;
		for (Country c: countryList) {
			if (c.getName().equalsIgnoreCase(search)) {
				countryCode = (c.getCode());
				found=true;
				break;
			}
		}
		if (!found) {
			List<Country> countryListFound = new ArrayList<Country>();
			System.out.println("Country not found as a full name, I'll proceede with a partial-name search");
			for (Country c: countryList) {
				if (c.getName().toUpperCase().startsWith(search.toUpperCase())) {
					countryListFound.add(c);
				}
				if (countryListFound.size()>1)
					break;
			}
			if (!countryListFound.isEmpty()) {
				if (countryListFound.size() == 1) {
					countryCode = countryListFound.get(0).getCode();
					System.out.println("Retrieve result for: " + countryListFound.get(0).getName());
					found = true;
				}
				else if (countryListFound.size() > 1){
					System.out.println("Partial-name search done, but found multiple country: please, be more specific!");
				}
			}
			else {
				System.out.println("Country NOT found!");
			}
		}
		if (found) {
			return retrieveRunwayByCountryCode(countryCode);
		}
		return runways;
	}
	
	public List<Country> retrieveTopTenCountry(){
		if (this.topTenCountry!=null && !this.topTenCountry.isEmpty())
			return this.topTenCountry;
		
		Map<String, Integer> airportMap = new HashMap<>();
		List<String> topTenCountryCode = new ArrayList<>();
		List<Country> topTenCountry = new ArrayList<>();
		for (Airport a : airportList) {
			if (airportMap.containsKey(a.getCountryCode()))
				airportMap.put(a.getCountryCode(), airportMap.get(a.getCountryCode()) + 1); 
			else
				airportMap.put(a.getCountryCode(), 1); 

		}
		for (int i=0; i<10; i++) {
			String actualMaxCC = Collections.max(airportMap.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
			topTenCountryCode.add(actualMaxCC);
			airportMap.remove(actualMaxCC);
		}
		for (Country c : countryList) {
			if (topTenCountryCode.contains(c.getCode()))
				topTenCountry.add(c);
			if (topTenCountry.size()==10)
				break;
		}
		return this.topTenCountry = topTenCountry;
	}
}
