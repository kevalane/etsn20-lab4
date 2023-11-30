import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringSearch {
    public static void main(String[] args) {
        // Check if the correct number of arguments are provided
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: java StringSearch [-i] <pattern> <file>");
            return;
        }

        boolean caseInsensitive = false;
        String pattern = args[0];
        String fileName = args[1];

        // Check for optional -i argument
        if (args.length == 3 && args[0].equals("-i")) {
            caseInsensitive = true;
            pattern = args[1];
            fileName = args[2];
        }

        try {
            // Open the file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            /**
             * Compile the pattern with or without case sensitivity
             * depending on the -i argument
             * 
             * Checks for matching pattern using regex recognition
             */
            Pattern compiledPattern = caseInsensitive ? 
                                      Pattern.compile(pattern, Pattern.CASE_INSENSITIVE) :
                                      Pattern.compile(pattern);

            /**
             * Read the file line by line and don't stop until the end of the file
             * 
             * Check if the line matches the pattern
             * If it does, print the line
             */
            while ((line = reader.readLine()) != null) {
                Matcher matcher = compiledPattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(line);
                }
            }

            // Close the file
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

