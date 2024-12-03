package realtimeweb.weatherservice.domain;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Example usage of Forecast.parse()
        Map<String, Object> raw = fetchData(); // Replace with actual data fetching logic
        ArrayList<Forecast> forecasts = Forecast.parse(raw);

        for (Forecast forecast : forecasts) {
            System.out.println(forecast);
        }
    }

    private static Map<String, Object> fetchData() {
        // Placeholder for fetching weather data
        return null;
    }
}
