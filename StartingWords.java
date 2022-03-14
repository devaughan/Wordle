/*
user inputs the number of words they want outputed
code will return the the top words that have the highest scores
all letters are mutually exclusive between words

doesn't work
runtime is way too long

use recursion and give it an alphabet with booleans set for true if that letter has been used already
add the word if all those letters are unused and use recursion again

could use gray and make a smaller wordlist every time a new word is added to remove all words without those letters

words cannot have duplicate letters

want to be to store or look at all the words that 

*/
import java.util.Scanner;

public class StartingWords {

    // replace this with an arraylist
    public static RankedWord[] BestWords; // Array to contain the best words w/o duplicate letters
    public static int BestWordsValue = 0;
    public static int numberOfWords;
    public static RankedWord[] wordList;

    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);

        // 0 < numberOfWords < 6
        System.out.print("How many words: ");
        numberOfWords = input.nextInt();

        input.close();

        RankedWord[] tempWords = new RankedWord[numberOfWords]; // array to store best words and compare to bestWords value;
        int tempWordsValue = 0;

        BestWords = new RankedWord[numberOfWords];

        Wordle wordle = new Wordle();
        wordList = wordle.toArray(); // array of all wordle words
        //RankedWord[] tempList = wordList; // array to contain all words w/o the letters of the prior words in BestWords

        getNextWord(tempWords, tempWordsValue, 0);

        System.out.println(BestWordsValue);
        bestWordsWordle.printWords(BestWords);

    }

    
    public static void getNextWord(RankedWord[] tempWords, int tempWordsValue, int index)
    {
        if (index == 0)
        {
            for (RankedWord i : wordList)
            {
                if (!i.getDuplicateLetters())
                {
                    tempWords[index] = i;
                    getNextWord(tempWords, tempWordsValue + i.getScore(), index + 1);
                }
            }
        }
        else if (index < numberOfWords)
        {
            for (RankedWord i : wordList)
            {
                if (!i.getDuplicateLetters() && isMutuallyExclusive(i, tempWords, index))
                {    
                    tempWords[index] = i;
                    getNextWord(tempWords, tempWordsValue + i.getScore(), index + 1);
                }
            }
        }
        else if (index == numberOfWords && tempWordsValue > BestWordsValue)
        {
            // this can't use printwords lol 
            // either i should change printwords or just print it myself
            // the order doesn't matter so should just make a new print
            bestWordsWordle.printWords(tempWords);
            System.out.println(tempWordsValue);
            BestWords = tempWords;
            BestWordsValue = tempWordsValue;
        }
    }

    // this is dumb af delete this
    public static boolean isMutuallyExclusive(RankedWord a, RankedWord[] array, int index)
    {
        RankedWord[] temp = new RankedWord[array.length];
        int tempIndex = 0;

        for (RankedWord i : array)
        {
            if (!i.contains('a'))
            {
                temp[tempIndex] = i;
                tempIndex++;
                return false;
            }
        }
        return true;
    }
}