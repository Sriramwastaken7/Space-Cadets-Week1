import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

public class EmailToName {
    public static void main(String[] args) throws IOException {

        //Create a bufferreader object with system.in for user input of email
        BufferedReader buffereader = new BufferedReader(new InputStreamReader(System.in));

        //Asking user for a soton email and storing input in a variable called string
        System.out.print("Enter an email address: ");
        String email = buffereader.readLine();

        //Taking the stuff from before @ only as we do not need the @soton.ac.uk
        String userID = email.substring(0, email.indexOf("@"));

        //Adding base url and adding the name from the email from uder input to the end of the url
        String baseUrl = "https://www.ecs.soton.ac.uk/people/";
        String url = baseUrl + userID;

        //creating an url object with the full url and a bufferreader object to read the stuff from thr url
        URLConnection connection = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        //Declaring a String line to store each line from the url
        String line;

        //While loop to iiterate through each line of the url while it isn't empty and storing that line inside the
        // line String we created
        while ((line = reader.readLine()) != null) {

            //if condition to check for the og:title tag as that is the line which has the name info
            if (line.contains("og:title")) {

                //getting a start index to nsplit string to only get the name by getting index of the ig:title tag
                int indexStart = line.indexOf("og:title");
                //getting an end index by finding index of the stuff right after the name info
                int indexEnd = line.indexOf("\" />");

                //splitting the line of code using the start and end index we found and storing the name in a string , i didn't add rest of the stuff
                // to the start index to make it neater as i didn't have time need to update it in the near future
                String name = line.substring(indexStart + "og:title\" content=\"".length(), indexEnd);

                //printing out the name retrivied using info from the email
                System.out.println(name);
            }
        }
    }
}