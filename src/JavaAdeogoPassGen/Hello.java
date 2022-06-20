import java.util.*;

import javax.naming.directory.SearchResult;

public class Hello {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Data usersInfo = new Data();
        char choice = ' ';
        int symbolAmount = 0;
        boolean convertToSymbol = false;
        boolean enterData = false;
        while (choice != 'y' && choice != 'n') {
            System.out.println("Do you want to enter a password (enter y or n)");
            choice = myObj.next().charAt(0);
            choice = Character.toLowerCase(choice);
        }

        enterData = (choice == 'y') ? true : false;

        if (enterData) {
            myObj.nextLine();
            System.out.println("Enter your username");
            usersInfo.m_Username = myObj.next();
            myObj.nextLine();

            System.out.println("Enter Business Name");
            usersInfo.m_Business = myObj.nextLine();

            System.out.println("Enter Special Word");
            usersInfo.m_SpecialWord = myObj.nextLine();

            while (symbolAmount < 1 || symbolAmount > 3) {
                System.out.println("How many symbols do you want 1-3");
                System.out.println("1 = symbol before, 2 = symbol after, 3 = both places");
                symbolAmount = myObj.nextInt();
            }
            System.out.println("do you want to convert letters to symbols");
            choice = myObj.next().charAt(0);
            convertToSymbol = (choice == 'y') ? true : false;
            usersInfo.m_Password = usersInfo.CreatePass(usersInfo.m_Business, usersInfo.m_SpecialWord, symbolAmount,
                    convertToSymbol);

            System.out.println(usersInfo.m_Password);
            usersInfo.m_Business = usersInfo.RemoveSpace(usersInfo.m_Business);
            usersInfo.m_SpecialWord = usersInfo.RemoveSpace(usersInfo.m_SpecialWord);
            FileManipulation.WriteToFileTxt(usersInfo);
        } else {
            char searchOption;
            String search;
            List<Data> searchResults = new ArrayList<Data>();
            do {
                System.out.println("What is your username");
                search = myObj.next();
                searchResults = FileManipulation.GetAllData(search);

                if (searchResults.size() == 0) {
                    System.out.println("No data found");
                } else {
                    System.out.println("Business Name       SpecialWord     PassWord");
                    for (int i = 0; i < searchResults.size(); i++) {
                        System.out.println(searchResults.get(i).m_Business + "  " + searchResults.get(i).m_SpecialWord
                                + "  " + searchResults.get(i).m_Password);
                    }
                }

                System.out.println("Do you want to search again");
                choice = myObj.next().charAt(0);
                choice = Character.toLowerCase(choice);
            } while (choice == 'y');

        }

    }

    List<Data> SearchUsingKeyWord() {

        System.out.println(
                "Dyu want so search for password using business - 'b', password - p, special word - s or username - u");
        searchOption = myObj.next().charAt(0);
        searchOption = Character.toLowerCase(searchOption);

        System.out.println("Enter your search");
        search = myObj.next();
        switch (searchOption) {
            case 'b':
                searchResults = FileManipulation.SearchBusinessNameFromFile(search);
                break;
            case 'p':
                searchResults = FileManipulation.SearchPassFromFile(search);
                break;
            case 's':
                searchResults = FileManipulation.SearchWordFromFile(search);
                break;
            case 'u':
                searchResults = FileManipulation.SearchPassFromFileUsingUserName(search);
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
        return searchResults;
    }

}