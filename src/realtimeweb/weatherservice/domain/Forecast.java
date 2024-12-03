package realtimeweb.weatherservice.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a weather forecast for a specific time period.
 */
public class Forecast {
    private String longDescription;
    private String description;
    private String imageUrl;
    private String temperatureLabel;
    private String periodName;
    private Integer probabilityOfPrecipitation;
    private String periodTime;
    private Integer temperature;

    // Constructor
    public Forecast(String periodName, String periodTime, String temperatureLabel,
                    int temperature, int probabilityOfPrecipitation, String description,
                    String imageUrl, String longDescription) {
        this.periodName = periodName;
        this.periodTime = periodTime;
        this.temperatureLabel = temperatureLabel;
        this.temperature = temperature;
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
        this.description = description;
        this.imageUrl = imageUrl;
        this.longDescription = longDescription;
    }

    // Getters
    public String getLongDescription() {
        return longDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTemperatureLabel() {
        return temperatureLabel;
    }

    public String getPeriodName() {
        return periodName;
    }

    public Integer getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public String getPeriodTime() {
        return periodTime;
    }

    public Integer getTemperature() {
        return temperature;
    }

    // toString method for debugging or display
    @Override
    public String toString() {
        return "Forecast[" +
                "periodName='" + periodName + '\'' +
                ", periodTime='" + periodTime + '\'' +
                ", temperatureLabel='" + temperatureLabel + '\'' +
                ", temperature=" + temperature +
                ", probabilityOfPrecipitation=" + probabilityOfPrecipitation +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ']';
    }

    /**
     * Parses raw JSON-like data into a list of Forecast objects.
     *
     * @param raw The raw JSON-like map containing forecast data.
     * @return A list of Forecast objects.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Forecast> parse(Map<String, Object> raw) {
        // Extract raw data
        HashMap<String, Object> rawTime = (HashMap<String, Object>) raw.get("time");
        HashMap<String, Object> rawData = (HashMap<String, Object>) raw.get("data");

        // Extract individual fields
        ArrayList<String> periodNames = (ArrayList<String>) rawTime.get("startPeriodName");
        ArrayList<String> periodTimes = (ArrayList<String>) rawTime.get("startValidTime");
        ArrayList<String> tempLabels = (ArrayList<String>) rawTime.get("tempLabel");
        ArrayList<String> temperatures = (ArrayList<String>) rawData.get("temperature");
        ArrayList<String> pops = (ArrayList<String>) rawData.get("pop");

        // Replace null probabilities of precipitation with "0"
        for (int i = 0; i < pops.size(); i++) {
            if (pops.get(i) == null) {
                pops.set(i, "0");
            }
        }

        ArrayList<String> weather = (ArrayList<String>) rawData.get("weather");
        ArrayList<String> icons = (ArrayList<String>) rawData.get("iconLink");
        ArrayList<String> text = (ArrayList<String>) rawData.get("text");

        // Build Forecast objects
        ArrayList<Forecast> forecasts = new ArrayList<>();
        for (int i = 0; i < periodNames.size(); i++) {
            forecasts.add(new Forecast(
                    periodNames.get(i),
                    periodTimes.get(i),
                    tempLabels.get(i),
                    Integer.parseInt(temperatures.get(i)),
                    Integer.parseInt(pops.get(i)),
                    weather.get(i),
                    icons.get(i),
                    text.get(i)
            ));
        }

        return forecasts;
    }
}
