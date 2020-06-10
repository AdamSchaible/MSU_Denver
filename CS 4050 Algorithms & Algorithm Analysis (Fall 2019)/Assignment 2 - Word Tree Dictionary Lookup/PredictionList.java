

//Author Adam Schaible
//CS4050
//Programming Assignment 2
//

import java.util.Arrays;
import java.util.List;

public class PredictionList {

    public List<String> getStringArray(Trie.TrieNode node, List<String> resultsList, boolean stillInsideDictionary)
    {


        //stuff below worked for just typing and not deleting
        if(node != null)
        {
            if(node.isEndOfWord)
            {
                resultsList.add(node.wordInDictionary);
            }
        }
        List<Trie.TrieNode> children = Arrays.asList(node.children);

        for (Trie.TrieNode child : children)
        {
            if(child != null) {
                getStringArray(child, resultsList,stillInsideDictionary);
            }
        }

        return resultsList;
    }
}