package oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject; // Requires org.json library
import java.time.LocalTime;

public class ex15 extends Application {
    private int time = 0;
    private XYChart.Series<Number, Number> series = new XYChart.Series<>();

    @Override
    public void start(Stage stage) {
        // Axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (seconds)");
        yAxis.setLabel("BTC Price (USD)");

        // Line chart setup
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Real-Time Bitcoin Price (USD)");
        series.setName("BTC/USD");
        lineChart.getData().add(series);

        // Timeline for real-time updates every 5 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            double price = getBitcoinPrice();
            if (price > 0) {
                series.getData().add(new XYChart.Data<>(time++, price));
                if (series.getData().size() > 20) {
                    series.getData().remove(0);
                }
                System.out.println(LocalTime.now() + " → BTC: $" + price);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Scene setup
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Real-Time Bitcoin Price Tracker");
        stage.show();
    }

    // Fetch real-time Bitcoin price
    private double getBitcoinPrice() {
        try {
            URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice/USD.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            return json.getJSONObject("bpi").getJSONObject("USD").getDouble("rate_float");
        } catch (Exception e) {
            System.out.println("Error fetching Bitcoin price: " + e.getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
