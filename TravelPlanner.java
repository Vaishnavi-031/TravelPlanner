import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Destination {
    private final String name;
    private final Date startDate;
    private final Date endDate;
    private final String weather;
    private final double budget;

    public Destination(String name, Date startDate, Date endDate, double budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.weather = getWeatherInfo(name); // Placeholder for weather info
    }

    // Getters
    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getWeather() {
        return weather;
    }

    public double getBudget() {
        return budget;
    }

    // Placeholder for getting weather information
    private String getWeatherInfo(String location) {
        return "Sunny"; // Replace with actual API call
    }

    // Generate Google Maps URL for the destination
    public String getMapUrl() {
        return "https://www.google.com/maps/search/?api=1&query=" + name.replace(" ", "+");
    }
}

class Itinerary {
    private final ArrayList<Destination> destinations;

    public Itinerary() {
        this.destinations = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        this.destinations.add(destination);
    }

    public void printItinerary() {
        double totalBudget = 0;
        System.out.println("Travel Itinerary:");
        for (Destination dest : destinations) {
            System.out.printf("Destination: %s%nStart Date: %s%nEnd Date: %s%nWeather: %s%nBudget: $%.2f%nMap: %s%n%n",
                              dest.getName(), dest.getStartDate(), dest.getEndDate(), dest.getWeather(), dest.getBudget(), dest.getMapUrl());
            totalBudget += dest.getBudget();
        }
        System.out.printf("Total Budget: $%.2f%n", totalBudget);
    }
}

public class TravelPlanner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Itinerary itinerary = new Itinerary();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            while (true) {
                System.out.print("Enter destination (or type 'done' to finish): ");
                String destinationName = scanner.nextLine();
                if (destinationName.equalsIgnoreCase("done")) {
                    break;
                }
                
                Date startDate = null;
                Date endDate = null;
                while (startDate == null || endDate == null) {
                    try {
                        System.out.print("Enter start date (yyyy-mm-dd): ");
                        startDate = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter end date (yyyy-mm-dd): ");
                        endDate = dateFormat.parse(scanner.nextLine());
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please try again.");
                    }
                }
                
                System.out.print("Enter budget for this destination: ");
                double budget = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character
                
                Destination destination = new Destination(destinationName, startDate, endDate, budget);
                itinerary.addDestination(destination);
            }
            
            itinerary.printItinerary();
        }
    }
}
