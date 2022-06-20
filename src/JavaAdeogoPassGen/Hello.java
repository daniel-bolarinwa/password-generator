import java.io.FileNotFoundException;
import java.util.*;

import javax.naming.directory.SearchResult;

public class Hello {

    public static void main(String[] args) throws FileNotFoundException {
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
                FileManipulation.SearchFromFile2(search);

                System.out.println("Do you want to search again");
                choice = myObj.next().charAt(0);
                choice = Character.toLowerCase(choice);
            } while (choice == 'y');

        }

    }

}