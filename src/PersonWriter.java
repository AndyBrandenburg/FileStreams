import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonWriter {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);

        Person person;
        ArrayList<Person> recs = new ArrayList<>();



       // ArrayList<String> recs = new ArrayList<>();

        String id;
        String firstName;
        String lastName;
        String title;
        int yearOfBirth;

        String rec;

        boolean done = false;

        do {

            id = SafeInput.getNonZeroLenString(in, "Enter the ID ");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name: ");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name: ");
            title = SafeInput.getNonZeroLenString(in, "What is your title? ");
            yearOfBirth = SafeInput.getInt(in, "What year were you born? ");






            person = new Person(id, firstName, lastName, title, yearOfBirth);
            recs.add(person);




            done = SafeInput.getYNConfirm(in, "Are you done? ");


        }while(!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(Person r : recs)
            {
                writer.write(r.toCSVDataString(), 0, r.toCSVDataString().length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

//                writer.write(r.toJSONDataString(), 0, r.toJSONDataString().length());  // stupid syntax for write rec
//                // 0 is where to start (1st char) the write
//                // rec. length() is how many chars to write (all)
//                writer.newLine();  // adds the new line

//                writer.write(r.toXmlDataString(), 0, r.toXmlDataString().length());  // stupid syntax for write rec
//                // 0 is where to start (1st char) the write
//                // rec. length() is how many chars to write (all)
//                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

}
