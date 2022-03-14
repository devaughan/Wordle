public class RankedWord 
{

    public String stringValue;
    int score;
    boolean duplicateLetters = false;

    // Constructor
    public RankedWord(String w) 
    {
        stringValue = w;
        score = 0;
        duplicateLetters = checkDuplicateLetters();
    }

    public RankedWord(String w, int s, String b)
    {
        stringValue = w;
        score = s;
        if (b.equals("true"))
        {
            duplicateLetters = true;
        }
        else 
        {
            duplicateLetters = false;
        }
    }

    RankedWord()
    {
        stringValue = "";
        score = 0;
        duplicateLetters = false;
    }

    public String getStringValue() 
    {
        return stringValue;
    }

    public int getScore() 
    {
        return score;
    }

    public boolean getDuplicateLetters()
    {
        return duplicateLetters;
    }

    public void setStringValue(String w) 
    {
        stringValue = w;
        duplicateLetters = checkDuplicateLetters();
    }

    public void setScore(int v)
    {
        score = v;
    }

    public void setDuplicateLetters(boolean b)
    {
        duplicateLetters = b;
    }

    public boolean contains(char c)
    {
        for (int i = 0; i < stringValue.length(); i++)
        {
            if (stringValue.charAt(i) == c)
            {
                return true;
            }
        }
        return false;
    }

    // always returns true
    // fix!!!!!!!!
    public boolean checkDuplicateLetters() 
    {
        char c;
        for ( int i = 0; i < stringValue.length(); i++)
        {
            c = stringValue.charAt(i);
            for (int j = i + 1; j < stringValue.length(); j++)
            {
                if (c == stringValue.charAt(j))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int compare(RankedWord newWord)
    {
        return stringValue.compareTo(newWord.getStringValue());
    }

    public String toString() 
    {
        return(stringValue + " " + score + " " + duplicateLetters);
    }
}