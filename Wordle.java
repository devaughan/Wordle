// program to find the best words in wordle based on  
// the frequency of each letter in the word list
// https://www.powerlanguage.co.uk/wordle/

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Wordle {
    
    public static int[] letterCount = new int[26]; // counts the frequency of every letter a to z
    public static ArrayList<RankedWord> wordList; // array to contain all words in total word list from wordle
    public static HashMap<String, RankedWord> wordMap = new HashMap<String, RankedWord>();
    
    public static void main(String[] args) {

        wordList = new ArrayList<RankedWord>(); // array to contain all words in total word list from wordle

        // adds all the words in wordList.txt to wordList array
        try 
        {
            File wordListFile = new File("wordList.txt");
            File answerListFile = new File("answerList.txt");

            Scanner wordListInput = new Scanner(wordListFile);
            Scanner answerListInput = new Scanner(answerListFile);

            RankedWord nextWord;
            // this list is already sorted alphabetically
            while (wordListInput.hasNextLine()) 
            {
                nextWord = new RankedWord(wordListInput.nextLine());
                wordList.add(nextWord); 
            }

            // should insert the words as they fit alphabetically
            while (answerListInput.hasNextLine())
            {
                nextWord = new RankedWord(answerListInput.nextLine());
                sort(nextWord);
            }

            wordListInput.close();
            answerListInput.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("file does not exist");
        }

        // goes through wordlist and counts the frequency of every letter
        for (RankedWord index : wordList) 
        {
            boolean repeatLetter = false;
            char c;
            String word = index.getStringValue();

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
        for (RankedWord nextWord : wordList) 
        {
            int theScore = evaluateScore(nextWord);
            nextWord.setScore(theScore);
            wordMap.put(nextWord.getStringValue(), nextWord);
        }

        String newFileName = "WordBank.txt";
        File newFile = new File(newFileName);
        FileWriter wordBank;
        try 
        {
            wordBank = new FileWriter(newFile);
            String nextLine;
            for (int i = 0; i < wordList.size(); i++)
            {
                nextLine = wordList.get(i).toString() + "\n";
                try {
                    wordBank.write(nextLine);
                }
                catch (IOException e)
                {
                    System.out.println("failed to write word");
                }
            }
            wordBank.close();
        }
        catch (IOException e)
        {
            System.out.println("Failed");
        }
    }

    public RankedWord getIndex(int index)
    {
        return wordList.get(index);
    }

    public int getNumberOfEntries()
    {
        return wordList.size();
    }

    public RankedWord[] toArray()
    {
        return wordList.toArray(new RankedWord[wordList.size()]);
    }
        
    // prints the alphabet and their frequency 
    public static void printLetterCount() 
    {
        for (int i = 0; i < 26; i++) 
        {
            System.out.println((char)(i + 'a') + ": " + letterCount[i]);
        }
    }

    // evaluates the word by taking the average frequency of every letter
    public static int evaluateScore(RankedWord theWord) 
    {
        int score = 0;
        String theString = theWord.getStringValue();
        boolean repeatLetter = false;
        char c;

        for (int index = 0; index < 5; index++) 
        {
            c = theString.charAt(index);
            for (int i = 0; i < index; i++) 
            {
                if (c == theString.charAt(i)) 
                {
                    repeatLetter = true;
                    break;
                }
            }
            // doesn't add to the word's value/score if the letter is a repeat
            if (!repeatLetter) 
            {
                score += letterCount[c - 'a'];
            }

            repeatLetter = false;
        }
        
        score = (int)(score / 5);

        return score;
    }   

    public static void sort(RankedWord newWord)
    {
        int wordListSize = wordList.size();
        sort(newWord, 0, wordListSize);
    }

    public static void sort(RankedWord newWord, int first, int last)
    {
        int mid = first + ((last - first) / 2);
        int num = newWord.compare(wordList.get(mid));
        if (first == mid)
        {
            wordList.add(mid + 1, newWord);
        }
        else if (num < 0)
        {
            sort(newWord, first, mid);
        }
        else 
        {
            sort(newWord, mid, last);
        }
    }
}