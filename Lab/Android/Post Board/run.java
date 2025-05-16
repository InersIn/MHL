import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CowsayUtil {

    // Set your script path here, e.g., "/data/data/com.yourapp/files/cowsay.sh"
    public static final String scriptPath = "/data/data/com.mobilehackinglab.postboard/files/cowsay.sh";

    public static void main(String[] args) {
        String cmd = "id; touch /dev/shm/inersin #";
        String out = runCowsay(cmd);
        System.out.println(out);
    } 

    public static String runCowsay(String message) {
        if (message == null) throw new IllegalArgumentException("message cannot be null");

        try {
            // Build the command
            String[] command = {"/bin/sh", "-c", scriptPath + ' ' + message};
            // Debug: Print command array
            System.out.println("Command array:");
            for (String part : command) {
                System.out.println(part);
            }

            // Start the process
            Process process = Runtime.getRuntime().exec(command);

            // Read output
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );

            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for process to finish
            process.waitFor();

            return output.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "cowsay: " + e.getMessage();
        }
    }
}
