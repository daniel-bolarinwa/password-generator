import java.util.*;

/**
 * Data
 */
public class Data {

    public String m_Username;
    public String m_Business;
    public String m_SpecialWord;
    public String m_Password;

    public String CreatePass(String Bus, String pass, int symbolAmount, boolean convertToSymbol) {
        char[] symbolsToAdd = new char[] { '!', '"', '$', '%', '^', '&', '*', '(', ')', '_', '-', '+', '/', '@', '#',
                '?' };
        Map<Character, Character> symbolsToChange = new HashMap<Character, Character>();
        symbolsToChange = CreateSymbolChange(symbolsToChange);
        Random rand = new Random(); // instance of random class
        int upperbound = 1998;
        int int_random = rand.nextInt(upperbound - 999);
        Bus = SentenceCase(Bus).replace(" ", "");
        pass = SentenceCase(pass).replace(" ", "");
        String conjoinedString = Bus.concat(pass) + String.valueOf(int_random);

        if (convertToSymbol) {
            char[] conjoinedChars = conjoinedString.toCharArray();
            for (int i = 0; i < conjoinedString.length(); i++) {

                char charToSympol = Character.toLowerCase(conjoinedString.charAt(i));
                if (symbolsToChange.containsKey(charToSympol)) {
                    System.out.println(charToSympol);
                    conjoinedChars[i] = symbolsToChange.get(charToSympol);
                }
            }
            conjoinedString = String.valueOf(conjoinedChars);
        }
        Random randSymbol = new Random(); // instance of random class
        int max = symbolsToAdd.length;
        int randSymbolToAdd;
        switch (symbolAmount) {
            case 1:
                conjoinedString = symbolsToAdd[rand.nextInt(max)] + conjoinedString;
                break;
            case 2:
                conjoinedString = conjoinedString + symbolsToAdd[rand.nextInt(max)];
                break;
            case 3:
                conjoinedString = symbolsToAdd[rand.nextInt(max)] + conjoinedString + symbolsToAdd[rand.nextInt(max)];
                break;
            default:
                break;
        }

        return conjoinedString;
    }

    public Map<Character, Character> CreateSymbolChange(Map<Character, Character> symbolsToChange) {
        symbolsToChange.put('a', '4');
        symbolsToChange.put('e', '3');
        symbolsToChange.put('i', '1');
        symbolsToChange.put('o', '0');
        symbolsToChange.put('s', '5');
        return symbolsToChange;
    }

    public static String RemoveSpace(String Word) {
        Word.replace(" ", "");
        return Word;
    }

    public static String ConvertToLower(String Word) {
        Word.toLowerCase();
        return Word;
    }

    public static String SentenceCase(String Word) {
        if (Word == null || Word.isEmpty()) {
            return Word;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : Word.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

}