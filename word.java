// word object used to store the string word and the value of the word
public class word 
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

    public void setValue(int v)
    {
        
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
                value += Wordle.letterCount[c - 'a'];
            }

            repeatLetter = false;
        }
        
        value = (int)(value / 5);
    }

    public boolean contains(char c)
    {
        for (int i = 0; i < 5; i++)
        {
            if (word.charAt(i) == c)
            {
                return true;
            }
        }
        return false;
    }

    public String toString() 
    {
        return(word + " " + value);
    }
}