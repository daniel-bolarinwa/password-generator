import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.File; // Import the File class
import java.io.IOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManipulation {

    public static void WriteToFileTxt(Data infoToSave) {
        String newFileName = infoToSave.m_Username + ".txt";
        File passwordFile = new File(newFileName);
        try {
            // File passwordFile = new File("filename.txt");
            if (passwordFile.createNewFile()) {
                System.out.println("File created: " + passwordFile.getName());
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
            // p.println(Data.RemoveSpace(infoToSave.m_Business) + " " +
            // Data.RemoveSpace(infoToSave.m_SpecialWord) + " " + infoToSave.m_passwordFile
            // + " " + infoToSave.m_Username);

            // adds the data to the file
            p.println(Data.RemoveSpace(infoToSave.m_Business) + " " + Data.RemoveSpace(infoToSave.m_SpecialWord) + " "
                    + infoToSave.m_Password);
            p.flush();
            System.out.println("Data added");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    private static List<Data> SearchFromFile(String Username) {
        List<Data> allData = new ArrayList<Data>();// gets all the data from the file, each line of info in the file is
                                                   // an entry in this list
        List<String> passwordFile = new ArrayList<String>();

        String newFileName = Username + ".txt";

        // converts all to lowercase to allow for human error when searching
        String search = Data.ConvertToLower(Username);

        try {
            List<String> allLines = Files.readAllLines(Paths.get(newFileName));
            // for each line in the file make it a string which we assign to the variable
            // linr
            for (String line : allLines) {
                Data dataFromFile = new Data();
                // this saves the index of every position with a space in that line
                List<Integer> spacePos = new ArrayList<Integer>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        spacePos.add(i);
                    }
                }
                // this divides the line into words based on the position of the spaces and
                // places those words into the right string in the data
                dataFromFile.m_Business = line.substring(0, spacePos.get(0));
                dataFromFile.m_SpecialWord = line.substring((spacePos.get(0)) + 1, spacePos.get(1));
                dataFromFile.m_Password = line.substring(spacePos.get(1) + 1);
                allData.add(dataFromFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return allData;

    }

    public static List<Data> SearchPassFromFile(String Pass, String Username) {

        List<Data> possibleData = new ArrayList<Data>();

        List<Data> allData = SearchFromFile(Username);

        List<String> passwordFile = new ArrayList<String>();

        // now extract only the passwords for the all data list because that is what we
        // are looking for and then we make is lower case a well so its easier to search

        for (int i = 0; i < allData.size(); i++) {
            passwordFile.add(allData.get(i).m_passwordFile.toLowerCase());
        }

        // this check every word in the password list and if that word contains the word
        // you searched then it adds the proper password and the Data associated with it
        // to a new list
        // and when it iterates through the list it returns the list with that data
        for (int i = 0; i < passwordFile.size(); i++) {
            if (passwordFile.get(i).contains(search)) {
                possibleData.add(allData.get(i));
            }
        }
        return possibleData;

    }

    public static List<Data> SearchBusinessNameFromFile(String Business, String Username) {
        List<Data> possibleData = new ArrayList<Data>();

        List<Data> allData = SearchFromFile(Username);

        List<String> allBusiness = new ArrayList<String>();

        for (int i = 0; i < allData.size(); i++) {
            allBusiness.add(allData.get(i).m_Business.toLowerCase());
        }

        for (int i = 0; i < allBusiness.size(); i++) {
            if (allBusiness.get(i).indexOf(search) != -1) {
                possibleData.add(allData.get(i));
            }
        }
        return possibleData;
    }

    public static List<Data> SearchWordFromFile(String word) {

        List<Data> possibleData = new ArrayList<Data>();

        List<Data> allData = SearchFromFile(Username);

        List<String> allSpecialWord = new ArrayList<String>();

        for (int i = 0; i < allData.size(); i++) {
            allSpecialWord.add(allData.get(i).m_SpecialWord.toLowerCase());
        }

        for (int i = 0; i < allSpecialWord.size(); i++) {
            if (allSpecialWord.get(i).contains(search)) {
                possibleData.add(allData.get(i));
            }
        }
        return possibleData;
    }

    public static List<Data> GetAllData(String userName) {
        return SearchFromFile(userName);
    }

    public static List<Data> SearchPassFromFileUsingUserName(String userName) {

        List<Data> possibleData = new ArrayList<Data>();

        List<Data> allData = SearchFromFile(Username);

        List<String> allBusiness = new ArrayList<String>();

        for (int i = 0; i < allData.size(); i++) {
            allBusiness.add(allData.get(i).m_Username);
        }

        for (int i = 0; i < allBusiness.size(); i++) {
            if (allBusiness.get(i).equals(userName)) {
                possibleData.add(allData.get(i));
            }

        }
        return possibleData;

    }

}
