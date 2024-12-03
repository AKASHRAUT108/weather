package realtimeweb.weatherservice.gui;

import realtimeweb.weatherservice.domain.Forecast;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WeatherForecastGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public WeatherForecastGUI() {
        // Create the main frame
        frame = new JFrame("Weather Forecast Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // Set layout
        frame.setLayout(new BorderLayout());

        // Create a table model with column names
        String[] columnNames = {
                "Period Name", "Time", "Temp Label", "Temperature",
                "Precipitation (%)", "Description", "Image URL", "Long Description"
        };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add a button to load forecasts
        JButton loadButton = new JButton("Load Forecasts");
        loadButton.addActionListener(e -> loadForecasts());
        frame.add(loadButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Simulate loading forecasts
    private void loadForecasts() {
        // Mock data (you should replace this with real data)
        Map<String, Object> mockData = new HashMap<>();
        Map<String, Object> timeData = new HashMap<>();
        Map<String, Object> weatherData = new HashMap<>();

        // Mock time data
        timeData.put("startPeriodName", new ArrayList<>(Arrays.asList("Morning", "Afternoon")));
        timeData.put("startValidTime", new ArrayList<>(Arrays.asList("2024-10-23T06:00:00", "2024-10-23T12:00:00")));
        timeData.put("tempLabel", new ArrayList<>(Arrays.asList("Low", "High")));

        // Mock weather data
        weatherData.put("temperature", new ArrayList<>(Arrays.asList("70", "65")));
        weatherData.put("pop", new ArrayList<>(Arrays.asList("10", "30")));
        weatherData.put("weather", new ArrayList<>(Arrays.asList("Sunny", "Cloudy")));
        weatherData.put("iconLink", new ArrayList<>(Arrays.asList("http://example.com/sunny.png", "http://example.com/cloudy.png")));
        weatherData.put("text", new ArrayList<>(Arrays.asList("Clear skies", "Partly cloudy")));

        mockData.put("time", timeData);
        mockData.put("data", weatherData);

        // Parse mock data into forecasts
        ArrayList<Forecast> forecasts = Forecast.parse(mockData);

        // Clear the table
        tableModel.setRowCount(0);

        // Populate the table with forecasts
        for (Forecast forecast : forecasts) {
            tableModel.addRow(new Object[]{
                    forecast.getPeriodName(),
                    forecast.getPeriodTime(),
                    forecast.getTemperatureLabel(),
                    forecast.getTemperature(),
                    forecast.getProbabilityOfPrecipitation(),
                    forecast.getDescription(),
                    forecast.getImageUrl(),
                    forecast.getLongDescription()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeatherForecastGUI::new);
    }
}
