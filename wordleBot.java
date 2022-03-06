import java.util.Scanner;

public class wordleBot {

    public static word[] potentialWords;
    public static int numberOfPotentialWords;

    public static void main(String[] args)
    {

        Wordle wordList = new Wordle();

        potentialWords = wordList.toArray();
        numberOfPotentialWords = Wordle.wordListSize;

        //bestWordsWordle.printWords(potentialWords);

        String[] GrayLetters = new String[26];
        String[] YellowLetters = new String[5];
        String[] GreenLetters = new String[5];

        int grayLettersIndex = 0;

        String temp;
        int length;

        Scanner input = new Scanner(System.in);

        while (bestWordsWordle.BestWords(potentialWords) > 1) {

            System.out.print("Gray Letters: ");
            temp = input.nextLine();
            length = temp.length();
            for (int i = 0; i < length; i++)
            {
                if (temp.charAt(i) != ' ')
                {
                    GrayLetters[grayLettersIndex] = String.valueOf(temp.charAt(i));
                    grayLettersIndex++;
                }
            }

            System.out.println("Put number Location next to letter");
            System.out.print("Yellow Letters: ");
            temp = input.nextLine();
            for (int i = 0; i < temp.length(); i++)
            {
                if (temp.charAt(i) != ' ')
                {
                    if (YellowLetters[temp.charAt(i + 1) - '0' - 1] == null)
                    {
                        YellowLetters[temp.charAt(i + 1) - '0' - 1] = String.valueOf(temp.charAt(i));
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
                if (GrayLetters[i] != null)
                {
                    gray(GrayLetters[i].charAt(0), i);
                }
            }

            System.out.println();

        }

        input.close();

    }   

    public static void green(char c, int index)
    {
        word[] temp = new word[numberOfPotentialWords];
        int tempIndex = 0;

        for (word i : potentialWords)
        {
            if (i == null)
            {
                break;
            }
            if (i.getWord().charAt(index) == c)
            {
                temp[tempIndex] = i;
                tempIndex++;
            }
        }
        potentialWords = temp;
    }

    public static void yellow(char c, int index)
    {
        word[] temp = new word[numberOfPotentialWords];
        int tempIndex = 0;

        for (word i : potentialWords)
        {
            if (i == null)
            {
                break;
            }
            else if (i.getWord().charAt(index) != c && i.contains(c))
            {
                temp[tempIndex] = i;
                tempIndex++;
            }
        }
        potentialWords = temp;
    }

    public static void gray(char c, int index)
    {
        word[] temp = new word[numberOfPotentialWords];
        int tempIndex = 0;

        for (word i : potentialWords)
        {
            if (i == null)
            {
                break;
            }
            else if (!i.contains(c))
            {
                temp[tempIndex] = i;
                tempIndex++;
            }
        }
        potentialWords = temp;
    }

    public static void printArray(String[] array)
    {
        for (int i = 0; i < array.length; i++) 
        {
            if (array[i] != null)
            {
                System.out.println(array[i]);
            }
        }
    }

    public static word[] consolidateArray(word[] array)
    {
        word[] temp = new word[1];
        int tempIndex = 0;

        for (int index = 0; index < array.length; index++)
        {
            if (array[index] != null)
            {
                temp[tempIndex] = array[index];
                tempIndex++;
            }
            // word[] t;
        }
        return temp;
    }
}