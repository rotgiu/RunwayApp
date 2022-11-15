## Runway App


#Launch
To lunch the application, you have to install Java >=1.8 on your machine.
Then, you can find the file 'runwayApplication-1.0.jar' inside folder runwayApplication/target.

You can simply launch the jar from command line with:
    $ java -jar runwayApplication-1.0.jar
    
Please notice that, inside 'target', you have another folder: 'src/resources'. This cointains 3 .csv files that the app needs in order to works. 
So, if you want to move the jar, you have to export also the 'src' and put it in the same folder level of .jar file.


#Feature
1- Runways for each airport given a country code or country name. 
2- Top 10 countries with highest number of airports.

If you want to search by country name, the app support the search also for a partial name, assuming that only one country is returned. Otherwise, runways will not be returned and the related error message will appear.

Examples: 
    1) search by country name: 'can' -> only 1 result: Canada -> will be return all the runways for each airport in Canada.
    2) search by country name: 'us' -> multiple result -> error message
