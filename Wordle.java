// program to find the best words in wordle based on  
// the frequency of each letter in the word list
// https://www.powerlanguage.co.uk/wordle/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Wordle {
    
    public static int[] letterCount = new int[26]; // counts the frequency of every letter a to z
    public static word[] wordList; // array to contain all words in total word list from wordle
    public static final int wordListSize = 10657;
    public static final int answerListSize = 2315;

    Wordle() {

        wordList = new word[wordListSize + answerListSize]; // array to contain all words in total word list from wordle

        // adds all the words in wordList.txt to wordList array
        try 
        {
            File wordListFile = new File("wordList.txt");
            File answerListFile = new File("answerList.txt");

            Scanner wordListInput = new Scanner(wordListFile);
            Scanner answerListInput = new Scanner(answerListFile);

            int index = 0;

            while (wordListInput.hasNextLine()) 
            {
                wordList[index] = new word(wordListInput.nextLine());
                index++;
            }

            while (answerListInput.hasNextLine())
            {
                wordList[index] = new word(answerListInput.nextLine());
                index++;
            }

            wordListInput.close();
            answerListInput.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("file does not exist");
        }

        // goes through wordlist and counts the frequency of every letter
        for (word index : wordList) 
        {
        

            boolean repeatLetter = false;
            char c;
            String word = index.getWord();

            for (int charIndex = 0; charIndex < 5; charIndex++) 
            {
                c = word.charAt(charIndex);

                for (int i = 0; i < charIndex; i++) 
                {
                    if (c == word.charAt(i)) 
                    {
                        repeatLetter = true;
                        break;
                    }
                }
                // doesn't add to the word's value/score if the letter is a repeat
                if (!repeatLetter) 
                {
                    letterCount[c - 'a']++; // increments the element to the proportional letter a = 0, b = 1, c = 2...
                }

                repeatLetter = false;
            }
        }

        // determines the score or value for every word in the word list
        for (word index : wordList) 
        {
            index.evaluateValue();
        }
    }

    public word getIndex(int index)
    {
        return wordList[index];
    }

    public int getNumberOfEntries()
    {
        return wordList.length;
    }

    public word[] toArray()
    {
        return wordList;
    }
        
    // prints the alphabet and their frequency 
    public static void printLetterCount() 
    {
        for (int i = 0; i < 26; i++) 
        {
            System.out.println((char)(i + 'a') + ": " + letterCount[i]);
        }
    }
}