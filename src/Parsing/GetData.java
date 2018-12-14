package Parsing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetData {
    private static void getData() throws IOException {
        BufferedReader br = null;
        try {
            String APIKey = "1e36113b6ffb88a911f883bf85184eb9";
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Vancouver,CA&APPID=" + APIKey);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            String JSONWeather = sb.toString();
            Parser.parse("[" + JSONWeather + "]");
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public static String displayWeather() {
        String description = updatedDescription();
        Double temp = Parser.getTemp();
        String weatherString;
        weatherString = "It's " + temp + "C and the weather condition is " + description;
        return weatherString;
    }

    public static Image getImage() throws IOException {
        String description = updatedDescription();
       // String description = "Clear";
        Image weatherImage = null;
        switch (description) {
            case "Clear":
                weatherImage = ImageIO.read(new File("./src/Resources/sunny.png")).getScaledInstance(300,300, Image.SCALE_DEFAULT);
                break;
            case "Clouds":
                weatherImage = ImageIO.read(new File("./src/Resources/clouds.png")).getScaledInstance(450,300, Image.SCALE_DEFAULT);
                break;
            case "Snow":
                weatherImage = ImageIO.read(new File("./src/Resources/snow.png")).getScaledInstance(450,300, Image.SCALE_DEFAULT);
                break;
            case "Rain":
                weatherImage = ImageIO.read(new File("./src/Resources/rain.png")).getScaledInstance(300,300, Image.SCALE_DEFAULT);
                break;
        }
        return weatherImage;
    }
    public static String updatedDescription(){
        try {
            GetData.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Parser.getDescription();
    }
}
