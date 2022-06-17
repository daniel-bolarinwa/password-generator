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
        File PassWord = new File("filename.txt");
        try {
            // File PassWord = new File("filename.txt");
            if (PassWord.createNewFile()) {
                System.out.println("File created: " + PassWord.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try (FileWriter f = new FileWriter(PassWord, true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {

            p.println(Data.RemoveSpace(infoToSave.m_Business) + " " + Data.RemoveSpace(infoToSave.m_SpecialWord) + " "
                    + infoToSave.m_Password + " " + infoToSave.m_Username);
            p.flush();
            System.out.println("Data added");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    public static List<Data> SearchPassFromFile(String Pass) {

        List<Data> allData = new ArrayList<Data>();
        List<String> Password = new ArrayList<String>();
        List<Data> possibleData = new ArrayList<Data>();

        String search = Data.ConvertToLower(Pass);

        try {
            List<String> allLines = Files.readAllLines(Paths.get("filename.txt"));
            for (String line : allLines) {
                Data dataFromFile = new Data();
                List<Integer> spacePos = new ArrayList<Integer>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        spacePos.add(i);
                    }
                }
                String business = line.substring(0, spacePos.get(0));
                String word = line.substring((spacePos.get(0)) + 1, spacePos.get(1));
                String pass = line.substring(spacePos.get(1) + 1);
                dataFromFile.m_Business = business;
                dataFromFile.m_SpecialWord = word;
                dataFromFile.m_Password = pass;
                allData.add(dataFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < allData.size(); i++) {
            Password.add(allData.get(i).m_Password.toLowerCase());
        }

        for (int i = 0; i < Password.size(); i++) {
            if (Password.get(i).contains(search)) {
                possibleData.add(allData.get(i));
            }
        }
        return possibleData;

    }

    public static List<Data> SearchBusinessNameFromFile(String Business) {
        List<Data> allData = new ArrayList<Data>();
        List<String> allBusiness = new ArrayList<String>();
        List<Data> possibleData = new ArrayList<Data>();

        String search = Data.ConvertToLower(Business);

        try {
            List<String> allLines = Files.readAllLines(Paths.get("filename.txt"));
            for (String line : allLines) {
                Data dataFromFile = new Data();
                List<Integer> spacePos = new ArrayList<Integer>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        spacePos.add(i);
                    }
                }

                String business = line.substring(0, spacePos.get(0));
                String word = line.substring((spacePos.get(0)) + 1, spacePos.get(1));
                String pass = line.substring(spacePos.get(1) + 1);
                dataFromFile.m_Business = business;
                dataFromFile.m_SpecialWord = word;
                dataFromFile.m_Password = pass;
                allData.add(dataFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < allData.size(); i++) {
            allBusiness.add(allData.get(i).m_Business.toLowerCase());
        }

        for (int i = 0; i < allBusiness.size(); i++) {
            if (allBusiness.get(i).indexOf(search) != -1) {
                System.out.println("it contains " + allBusiness.get(i));
                possibleData.add(allData.get(i));
            }
            System.out.println("it contains " + allBusiness.get(i) + " " + search);
        }
        return possibleData;
    }

    public static List<Data> SearchWordFromFile(String word) {
        List<Data> allData = new ArrayList<Data>();
        List<String> allSpecialWord = new ArrayList<String>();
        List<Data> possibleData = new ArrayList<Data>();

        String search = Data.ConvertToLower(word);

        try {
            List<String> allLines = Files.readAllLines(Paths.get("filename.txt"));
            for (String line : allLines) {
                Data dataFromFile = new Data();
                List<Integer> spacePos = new ArrayList<Integer>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        spacePos.add(i);
                    }
                }
                String business = line.substring(0, spacePos.get(0));
                String SpecialWord = line.substring((spacePos.get(0)) + 1, spacePos.get(1));
                String pass = line.substring(spacePos.get(1) + 1);
                dataFromFile.m_Business = business;
                dataFromFile.m_SpecialWord = SpecialWord;
                dataFromFile.m_Password = pass;
                allData.add(dataFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static List<Data> SearchPassFromFileUsingUserName(String userName) {

        List<Data> allData = new ArrayList<Data>();
        List<String> allBusiness = new ArrayList<String>();
        List<Data> possibleData = new ArrayList<Data>();

        try {
            List<String> allLines = Files.readAllLines(Paths.get("filename.txt"));
            for (String line : allLines) {
                Data dataFromFile = new Data();
                List<Integer> spacePos = new ArrayList<Integer>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        spacePos.add(i);
                    }
                }

                String business = line.substring(0, spacePos.get(0));
                String word = line.substring((spacePos.get(0)) + 1, spacePos.get(1));
                String pass = line.substring(spacePos.get(1) + 1, spacePos.get(2));
                String user = line.substring(spacePos.get(2) + 1);
                dataFromFile.m_Business = business;
                dataFromFile.m_SpecialWord = word;
                dataFromFile.m_Password = pass;
                dataFromFile.m_Username = user;
                allData.add(dataFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < allData.size(); i++) {
            allBusiness.add(allData.get(i).m_Username);
        }

        for (int i = 0; i < allBusiness.size(); i++) {
            if (allBusiness.get(i).equals(userName)) {
                System.out.println("it contains " + allBusiness.get(i));
                possibleData.add(allData.get(i));
            }
            // System.out.println("it contains " + allBusiness.get(i) + " " + search);
        }
        return possibleData;

    }

}
