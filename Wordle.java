// program to find the best words in wordle based on  
// the frequency of each letter in the word list
// https://www.powerlanguage.co.uk/wordle/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Wordle {
    
    // counts the frequency of every letter a to z
    public static int[] letterCount = new int[26];

    public static void main(String[] args) {

        word[] wordList = new word[10657]; // array to contain all words in total word list from wordle
        word[] bestWords = new word[10]; // array to contain the ten best words

        // adds all the words in wordList.txt to wordList array
        try 
        {
            File wordListFile = new File("wordList.txt");

            Scanner fileInput = new Scanner(wordListFile);

            int index = 0;

            while (fileInput.hasNextLine()) 
            {
                wordList[index] = new word(fileInput.nextLine());
                index++;
            }

            fileInput.close();

        }
        catch (FileNotFoundException e)
        {
            System.out.println("file does not exist");
        }

        // goes through wordlist and counts the frequency of every letter
        for (word index : wordList) 
        {
            for (int charIndex = 0; charIndex < 5; charIndex++) 
            {
                char c = index.getWord().charAt(charIndex);
                letterCount[c - 'a']++; // increments the element to the proportional letter a = 0, b = 1, c = 2...
            }
        }

        // determines the score or value for every word in the word list
        for (word index : wordList) 
        {
            index.evaluateValue();
        }

        // creates blank words with value 0 int he bestwords array
        for (int index = 0; index < 10; index++)
        {
            bestWords[index] = new word();
        }

        // 
        for (int index = 0; index < wordList.length; index++) 
        {
            for (int i = 0; i < 10; i++)
            {
                if (wordList[index].getValue() > bestWords[i].getValue())
                {
                    bestWords[i] = wordList[index];
                    break;
                }
            }
        }

        // quick code to find the ten best words without having to use a sorting algorithm
        int count = 1;
        for (word index : bestWords)
        {
            if (count == 10) 
            {
                System.out.println(count+ ": " + index);

            }
            else 
            {
                System.out.println(count+ ":  " + index);

            }
            count++;
        }

    }

    // prints the alphabet and their frequency 
    public static void printLetterCount() 
    {
        for (int i = 0; i < 26; i++) 
        {
            System.out.println((char)(i + 'a') + ": " + letterCount[i]);
        }
    }

    // word object used to store the string word and the value of the word
    private static class word 
    {

        public String word;
        int value;

        word(String w) 
        {
            word = w;
        }

        word()
        {
            word = "";
            value = 0;
        }

        public String getWord() 
        {
            return word;
        }

        public int getValue() 
        {
            return value;
        }

        public void setWord(String w) 
        {
            word = w;
        }

        // evaluates the word by taking the average frequency of every letter
        public void evaluateValue() 
        {
            value = 0;
            boolean repeatLetter = false;
            char c;

            for (int index = 0; index < 5; index++) 
            {
                c = word.charAt(index);
                for (int i = 0; i < index; i++) 
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
                    value += letterCount[c - 'a'];
                }

                repeatLetter = false;
            }
            
            value = (int)(value / 5);
        }

        public String toString() 
        {
            return(word + " " + value);
        }
    }
}
