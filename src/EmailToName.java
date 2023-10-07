import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

public class EmailToName {
    public static void main(String[] args) throws IOException {
        BufferedReader buffereader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter an email address: ");
        String email = buffereader.readLine();
        String userID = email.substring(0, email.indexOf("@"));
        String baseUrl = "https://www.ecs.soton.ac.uk/people/";
        String url = baseUrl + userID;
        URLConnection connection = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("og:title")) {
                int indexStart = line.indexOf("og:title");
                int indexEnd = line.indexOf("\" />");
                String name = line.substring(indexStart + "og:title\" content=\"".length(), indexEnd);
                System.out.println(name);
            }
        }
    }
}