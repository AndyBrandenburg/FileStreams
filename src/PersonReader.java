import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.*;


import static java.nio.file.StandardOpenOption.CREATE;
public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedfile;
        String rec = "";
        Person person;
        ArrayList<Person> lines = new ArrayList<>();

        final int FIELDS_LENGTH = 5;

        String id, firstName, lastName, title;
        int yearOfBirth;

        try {

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedfile = chooser.getSelectedFile();
                Path file = selectedfile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");

                String[] fields;
                for (Person l : lines) {
                    fields = l.toCSVDataString().split(",");

                    if (fields.length == FIELDS_LENGTH) {
                        id = fields[0].trim();
                        firstName = fields[1].trim();
                        lastName = fields[2].trim();
                        title = fields[3].trim();
                        yearOfBirth = Integer.parseInt(fields[4].trim());
                        System.out.printf("\n%-8s%-25s%-25s%-6s%6d", id, firstName, lastName, title, yearOfBirth);
                    } else {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(l);
                    }
                }

            } else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

