// user inputs the number of words they want outputed
// code will return the the top words that have the highest scores
// all letters are mutually exclusive between words
import java.util.Scanner;

public class StartingWords {

    public static word[] BestWords; // Array to contain the best words w/o duplicate letters
    public static int BestWordsValue = 0;
    public static int numberOfWords;
    public static word[] wordList;


    public static void main(String[] args)
    {

        long startTime = System.nanoTime();

        Scanner input = new Scanner(System.in);

        System.out.print("How many words: ");
        numberOfWords = input.nextInt();

        input.close();

        word[] tempWords = new word[numberOfWords]; // array to store best words and compare to bestWords value;
        int tempWordsValue = 0;

        BestWords = new word[numberOfWords];

        Wordle wordle = new Wordle();
        wordList = wordle.toArray(); // array of all wordle words
        word[] tempList = wordList; // array to contain all words w/o the letters of the prior words in BestWords

        getNextWord(tempWords, tempWordsValue, 0);

        System.out.println(BestWordsValue);
        bestWordsWordle.printWords(BestWords);

        long endTime = System.nanoTime();

        System.out.println(endTime - startTime);

    }

    public static void getNextWord(word[] tempWords, int tempWordsValue, int index)
    {
        if (index == 0)
        {
            for (word i : wordList)
            {
                tempWords[index] = i;
                getNextWord(tempWords, tempWordsValue + i.getValue(), index + 1);
            }
        }
        else if (index < numberOfWords)
        {
            for (word i : wordList)
            {
                if (isMutuallyExclusive(i, tempWords, index))
                {    
                    tempWords[index] = i;
                    getNextWord(tempWords, tempWordsValue + i.getValue(), index + 1);
                }
            }
        }
        else if (index == numberOfWords && tempWordsValue > BestWordsValue)
        {
            bestWordsWordle.printWords(tempWords);
            System.out.println(tempWordsValue);
            BestWords = tempWords;
            BestWordsValue = tempWordsValue;
        }
    }

    public static word[] isMutuallyExclusive(word a, word[] array, int index)
    {
        word[] temp = new word[array.length];
        int tempIndex = 0;

        for (word i : array)
        {
            if (!i.contains())
            {
                temp[tempIndex] = i;
                tempIndex++;
            }
        }
        return temp;
    }

}