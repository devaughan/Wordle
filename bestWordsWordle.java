import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;

public class bestWordsWordle {

    public static void BestWords(ArrayList<RankedWord> array) 
    {        
        PriorityQueue<RankedWord> maxHeap = new PriorityQueue<RankedWord>(new Comparator<RankedWord>(){
            @Override
            public int compare(RankedWord w1, RankedWord w2)
            {
                return w2.getScore() - w1.getScore();
            }
        });

        for (int i = 0; i < array.size(); i++)
        {
            RankedWord nextWord = array.get(i);
            maxHeap.add(nextWord);
        }

        System.out.println("Best Words:");
        printWords(maxHeap);
    }

    // does not work
    // change either so it works the same as bestWords or so you input a boolean to determine whether you use a max or min heap
    /*
    public static int worstWords(RankedWord[] array)
    {
        int n = 0;
        RankedWord[] worstWords;

        worstWords = new RankedWord[10]; // array to contain the ten best words
        
        // creates blank words with value 0 in the bestwords array
        for (int index = 0; index < 10; index++)
        {
            worstWords[index] = new RankedWord();
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
                else if (array[index].getScore() < worstWords[i].getScore())
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
    */

    public static void printWords(PriorityQueue heap) 
    {
        for (int i = 0; i < 10; i++)
        {
            if (i >= 10) 
            {
                System.out.println(i + ": " + heap.poll());
            }
            else 
            {
                System.out.println(i + ":  " + heap.poll());
            }
        }
    }
}