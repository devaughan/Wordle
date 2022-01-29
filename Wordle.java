
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Wordle {


    public static int[] letterCount = new int[26];

    public static void main(String[] args) {

        word[] wordList = new word[10657];
        word[] bestWords = new word[10];

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

        for (word index : wordList) 
        {
            for (int charIndex = 0; charIndex < 5; charIndex++) 
            {
                char c = index.getWord().charAt(charIndex);
                letterCount[c - 'a']++;
                

            }
        }

        // printLetterCount();

        for (word index : wordList) 
        {
            index.evaluateValue();
        }

        for (int index = 0; index < 10; index++)
        {
            bestWords[index] = new word();
        }


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

    public static void printLetterCount() 
    {
        for (int i = 0; i < 26; i++) 
        {
            System.out.println((char)(i + 'a') + ": " + letterCount[i]);
        }
    }

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