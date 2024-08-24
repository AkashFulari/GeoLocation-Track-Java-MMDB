package org.akashfulari;

/**
 * @Author: Akash K. Fulari
 * @Contact-mail: akashfulari31@gmail.com
 * @Package: org.akashfulari
 * @Description: LocationTracker - Project Development
 * @Created: 2024/01/31 3:08 PM
 **/
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class Geolocation {

    public static void main(String[] args) {
        String ipAddr = "103.160.167.45";
        String databaseFilePath = "src/Assets/GeoLite2-City.mmdb"; // Adjust the path to your database file

        try {
            // Create a File object representing the GeoLite2 database file
            File database = new File(databaseFilePath);

            // Create a DatabaseReader using the GeoLite2 database
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            // Perform a lookup for the given IP address
            InetAddress ipAddress = InetAddress.getByName(ipAddr);
            CityResponse response = reader.city(ipAddress);

            // System.out.println(response);

            // Extract relevant information
            String ip_address = response.getTraits().getIpAddress();
            String country_code = response.getCountry().getIsoCode();
            String country_name = response.getCountry().getName();
            String state_code = response.getMostSpecificSubdivision().getIsoCode();
            String state_name = response.getMostSpecificSubdivision().getName();
            String city_name = response.getCity().getName();
            String zipcode = response.getPostal().getCode();
            double latitude = response.getLocation().getLatitude();
            double longitude = response.getLocation().getLongitude();

            // Display the results
            System.out.println("IP Address: " + ip_address);
            System.out.println("Country Code: " + country_code);
            System.out.println("Country Name: " + country_name);
            System.out.println("State Code: " + state_code);
            System.out.println("State Name: " + state_name);
            System.out.println("City Name: " + city_name);
            System.out.println("Zipcode: " + zipcode);
            System.out.println("Latitude: " + latitude);
            System.out.println("Longitude: " + longitude);

            // Close the DatabaseReader when done
            reader.close();

        } catch (IOException | GeoIp2Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
