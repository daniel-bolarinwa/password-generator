import java.util.ArrayList;
import java.util.List;
// Import the File class
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManipulation {

    public static void WriteToFileTxt(Data infoToSave) throws FileNotFoundException {
        String newFileName = infoToSave.m_Username + ".csv";
        File passwordFile = new File(newFileName);

        try {
            // File passwordFile = new File("filename.txt");
            if (passwordFile.createNewFile()) {
                PrintWriter out = new PrintWriter(passwordFile);
                System.out.println("File created: " + passwordFile.getName());
                out.printf("%s,%s,%s\n", "Business", "SpecialWord", "Password");
                out.flush();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try (FileWriter f = new FileWriter(passwordFile, true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {
            // this methos is if you want the username to be in the file and they get their
            // detail based on if there is a username that matches the search

            p.printf("%s, %s, %s\n", infoToSave.m_Business, infoToSave.m_SpecialWord, infoToSave.m_Password);
            p.flush();
            System.out.println("Data added");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public static void SearchFromFile2(String Username) {

        String newFileName = Username + ".csv";
        BufferedReader reader = null;
        String Line = "";

        try {
            reader = new BufferedReader(new FileReader(newFileName));
            while ((Line = reader.readLine()) != null) {
                String[] row = Line.split(",");
                for (String index : row) {
                    System.out.printf("%-30s", index);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
