public class bestWordsWordle {

    public static word[] bestWords;

    public static int BestWords(word[] array) 
    {

        int n = 0;
        bestWords = new word[10]; // array to contain the ten best words
        
        // creates blank words with value 0 in the bestwords array
        for (int index = 0; index < 10; index++)
        {
            bestWords[index] = new word();
        }

        // quick code to find the ten best words without having to use a sorting algorithm
        for (int index = 0; index < array.length; index++) 
        {
            for (int i = 0; i < 10; i++)
            {
                if (array[index] == null)
                {
                    break;
                }
                else if (array[index].getValue() > bestWords[i].getValue())
                {
                    n++;
                    bestWords[i] = array[index];
                    break;
                }
            }
        }

        System.out.println("Best Words:");
        printWords(bestWords);
        return n;
    }

    public static int worstWords(word[] array)
    {
        int n = 0;
        word[] worstWords;

        worstWords = new word[10]; // array to contain the ten best words
        
        // creates blank words with value 0 in the bestwords array
        for (int index = 0; index < 10; index++)
        {
            worstWords[index] = new word();
        }

        // quick code to find the ten best words without having to use a sorting algorithm
        for (int index = 0; index < array.length; index++) 
        {
            for (int i = 0; i < 10; i++)
            {
                if (array[index] == null)
                {
                    break;
                }
                else if (array[index].getValue() > worstWords[i].getValue())
                {
                    n++;
                    worstWords[i] = array[index];
                    break;
                }
            }
        }

        printWords(worstWords);
        return n;
    }

    public static void printWords(word[] array) 
    {
        int count = 1;
        for (word index : array)
        {
            if (index == null || index.getValue() == 0) 
            {
                break;
            }
            else if (count >= 10) 
            {
                System.out.println(count + ": " + index);

            }
            else 
            {
                System.out.println(count + ":  " + index);

            }
            count++;
        }
    }
}