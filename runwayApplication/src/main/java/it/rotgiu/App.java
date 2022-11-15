package it.rotgiu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

import it.rotgiu.model.Airport;
import it.rotgiu.model.Country;
import it.rotgiu.model.Runway;
import it.rotgiu.service.RunwayService;


/**
 * Hello world!
 *
 */
public class App 
{
	private static List<Airport> airportList;
	private static List<Country> countryList;
	private static List<Runway> runwayList;
	static boolean exit = false;
	private static RunwayService service;
	
	public static void main(String[] args) {
		System.out.println("Starting loading DB...");
		try {
			loadCsv();
		} catch (FileNotFoundException e) {
        	System.out.println("Please check if file are in the right location!");
        	e.printStackTrace();
        	return;
        } catch (Exception e1) {
        	e1.printStackTrace();
        	return;
        }
		System.out.println("DB loaded! Ready");

		service = new RunwayService(airportList, countryList, runwayList);
		
		runApp();
		
		return;

	}
	
	private static void loadCsv() throws Exception {
		airportList = new ArrayList<>();
		countryList = new ArrayList<>();
		runwayList = new ArrayList<Runway>();
        try {
        	String[] line;
        	//load ariport
        	
        	String resources = new File("").getAbsolutePath().concat("/src/resources");
        	CSVReader csvReader = new CSVReader(new FileReader(resources.concat("/airports.csv")));
        	csvReader.skip(1);
            while ((line = csvReader.readNext()) !=null) {
            	airportList.add(new Airport(line[8], Long.parseLong(line[0]), line[3]));
            }
            
            //load country
            csvReader = new CSVReader(new FileReader(resources.concat("/countries.csv")));
            csvReader.skip(1);
            while ((line = csvReader.readNext()) !=null) {
            	countryList.add(new Country(line[1], line[2]));
            }
            
            //load runway
            csvReader = new CSVReader(new FileReader(resources.concat("/runways.csv")));
            csvReader.skip(1);
            while ((line = csvReader.readNext()) !=null) {
            	runwayList.add(new Runway(Long.parseLong(line[0]), Long.parseLong(line[1])));
            }
            
        } catch (FileNotFoundException e) {
        	throw e;
        } catch (Exception e1) {
        	throw e1;
        }
	}
	
	private static void runApp() {
		
		System.out.println("Hello!");
		Scanner board = new Scanner(System.in);
		while (!exit) {
			System.out.println("These are the commands available:");
			System.out.println("1 - Search runway by country code or country name");
			System.out.println("2 - Retrieve top 10 countries with highest number of airports");
			System.out.println("0 - Exit and close");
			int input = -1;
			try {
				input = board.nextInt();
			} catch (InputMismatchException i) {
				System.out.println("Value not accepted");
				board = new Scanner(System.in);
				continue;
			}
			
			String search = "";
			switch (input) {
				case 1:
					boolean searchTypeChoised = false;
					while (!searchTypeChoised) {
						System.out.println("Do you want to search for country code (1) or country name (2)?");
						try {
							input = board.nextInt();
						} catch (InputMismatchException i) {
							System.out.println("Value not accepted");
							board = new Scanner(System.in);
							continue;
						}
						switch (input) {
							case 1:
							case 2:
								System.out.println("What i have to search?");
								do {
									search = board.nextLine();
								}
								while (search.isEmpty());
								break;
							default: 
								System.out.println("Unrecognized command!");
							
						}
						switch (input) {
							case 1:
								searchTypeChoised = true;
								Runway.printObjList(service.retrieveRunwayByCountryCode(search));
								break;
							case 2:
								searchTypeChoised = true;
								Runway.printObjList(service.retrieveRunwayByCountryName(search));
								break;
						}
					}
					break;
				case 2:
					Country.printObjList(service.retrieveTopTenCountry());
					break;
				default:
					System.out.println("Bye bye!");
					board.close();
					exit = true;
			}
		
		}
		
	}
	
	
}
