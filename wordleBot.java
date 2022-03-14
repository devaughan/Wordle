import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class wordleBot {

    public static ArrayList<RankedWord> potentialWords;
    public static int numberOfPotentialWords;

    public static void main(String[] args)
    {
        potentialWords = new ArrayList<RankedWord>();
        try {
            File wordBank = new File("WordBank.txt");
            Scanner wordBankInput = new Scanner(wordBank);
            String[] nextLine;
            RankedWord nextWord;
            while (wordBankInput.hasNextLine())
            {
                nextLine = wordBankInput.nextLine().split(" ");
                nextWord = new RankedWord(nextLine[0], Integer.valueOf(nextLine[1]), nextLine[2]);
                potentialWords.add(nextWord);
            }
            wordBankInput.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File does not exist");
        }

        //bestWordsWordle.printWords(potentialWords);

        boolean[] GrayLetters = new boolean[26];
        String[] YellowLetters = new String[5];
        String[] GreenLetters = new String[5];

        String temp;
        int length;

        Scanner input = new Scanner(System.in);

        while (potentialWords.size() > 1) {

            bestWordsWordle.BestWords(potentialWords);

            System.out.print("Gray Letters: ");
            temp = input.nextLine();
            length = temp.length();
            for (int i = 0; i < length; i++)
            {
                char nextChar = temp.charAt(i);
                if (nextChar > '`' && nextChar < '{')
                {
                    GrayLetters[nextChar - 'a'] = true;
                    System.out.println(nextChar - 'a');
                }
            }

            System.out.println("Put number Location next to letter");
            System.out.print("Yellow Letters: ");
            temp = input.nextLine();
            for (int i = 0; i < temp.length(); i++)
            {
                char nextChar = temp.charAt(i);
                char nextIndex = temp.charAt(i + 1);
                if (nextChar > '`' && nextChar < '{')
                {
                    if (YellowLetters[nextIndex - '0' - 1] == null)
                    {
                        YellowLetters[nextIndex - '0' - 1] = String.valueOf(nextChar);
                    }
                    else
                    {
                        YellowLetters[temp.charAt(i + 1) - '0' - 1] = YellowLetters[temp.charAt(i + 1) - '0' - 1].concat(String.valueOf(temp.charAt(i)));
                    }
                    i++;
                }
            }

            System.out.println("Put number Location next to letter");
            System.out.print("Green Letters: ");
            temp = input.nextLine();
            for (int i = 0; i < temp.length(); i++)
            {
                if (temp.charAt(i) != ' ' && Character.isLetter(temp.charAt(i)))
                {
                    GreenLetters[temp.charAt(i + 1) - '0' - 1] = String.valueOf(temp.charAt(i));
                }
            }

            for (int i = 0; i < GreenLetters.length; i++)
            {
                if (GreenLetters[i] != null)
                {
                    green(GreenLetters[i].charAt(0), i);
                }
            }

            for (int i = 0; i < YellowLetters.length; i++)
            {
                if (YellowLetters[i] != null)
                {
                    yellow(YellowLetters[i].charAt(0), i);
                }
            }
            
            for (int i = 0; i < GrayLetters.length; i++)
            {
                if (GrayLetters[i])
                {
                    gray((char)(i + 97));
                }
            }

            System.out.println();

        }

        input.close();

    }   

    public static void green(char c, int index)
    {
        for (int i = 0; i < potentialWords.size(); i++)
        {
            RankedWord nextWord = potentialWords.get(i);
            if (nextWord.getStringValue().charAt(index) != c)
            {
                potentialWords.remove(i);
                i--;
            }
        }
    }

    public static void yellow(char c, int index)
    {
        for (int i = 0; i < potentialWords.size(); i++)
        {
            RankedWord nextWord = potentialWords.get(i);
            if (nextWord.getStringValue().charAt(index) == c || !nextWord.contains(c))
            {
                potentialWords.remove(i);
                i--;
            }
        }
    }

    public static void gray(char c)
    {
        for (int i = 0; i < potentialWords.size(); i++)
        {
            RankedWord nextWord = potentialWords.get(i);
            if (nextWord.contains(c))
            {
                potentialWords.remove(i);
                i--;
            }
        }
    }

    public static void printArray(RankedWord[] array)
    {
        for (int i = 0; i < numberOfPotentialWords; i++) 
        {
            System.out.println(array[i]);
        }
    }
}