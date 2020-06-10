//Author Adam Schaible
//CS4050
//Programming Assignment 2
//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryTree
{

    public Trie getADictionaryTree() throws FileNotFoundException {
            List<String> theLines = new ArrayList<String>();
            Scanner input = new Scanner(new File("src/words.txt"));

            while (input.hasNextLine())
            {
                theLines.add(input.nextLine());
            }
            String[] dictionaryWords = theLines.toArray(new String[0]);

            //The for loop converts the entire dictionary to lowercase
            for(int i = 0; i < dictionaryWords.length; i++)
            {
                dictionaryWords[i]= dictionaryWords[i].toLowerCase();
            }
            Trie dictionaryTree = new Trie();

            //The for loop inserts each dictionary word into
            for(int i = 0; i < dictionaryWords.length; i++)
            {
                dictionaryTree.insert(dictionaryWords[i]);
            }
            return dictionaryTree;
        }

}
