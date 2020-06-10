//Name Adam Schaible
//CS4050
//Programming Assignment 2

// Trie class code is derived and modified from https://www.geeksforgeeks.org/trie-insert-and-search/
//comments made by myself(Adam Schaible) have "-AS" at the end of the comment line

public class Trie
{
	// Alphabet size (# of symbols)
	static final int ALPHABET_SIZE = 26;
	TrieNode root = new TrieNode(); //added line -AS

	// trie node 
	public class TrieNode //Changed from a static class to a public class -AS
	{ 
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		// isEndOfWord is true if the node represents
		// end of a word 
		boolean isEndOfWord;
		String wordInDictionary; //added this field to nodes -AS
		
		TrieNode(){ 
			isEndOfWord = false;
			wordInDictionary = ""; //line added to code -AS
			for (int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null; 
		} 
	}
    //static TrieNode root;//commented out and removed its associated comments -AS
	public void insert(String key) //Changed from a static method to a public method -AS
	{ 
        //moved int level into the for loop thats below -AS
		int length = key.length(); 
		int index; 
	
		TrieNode pCrawl = root; 
	
		for (int level = 0; level < length; level++)
		{ 
			index = key.charAt(level) - 'a'; 
			if (pCrawl.children[index] == null) 
				pCrawl.children[index] = new TrieNode();
			pCrawl = pCrawl.children[index]; 
		} 
	
		// mark last node as leaf 
		pCrawl.isEndOfWord = true;
		pCrawl.wordInDictionary = key; //line added into code -AS
	} 
	
	// method was originally named search, changed name to searchForSpotInTree, return was originally a boolean, changed to Object[],changed from a static method to a public method -AS
	public Object[] searchForSpotInTree(String key)
	{ 
		int level; 
		int length = key.length(); 
		int index; 
		TrieNode pCrawl = root;
		boolean possibleDictionaryEntries = true; //line added -AS
		for (level = 0; level < length; level++) 
		{ 
			index = key.charAt(level) - 'a'; 

			if (pCrawl.children[index] == null)
			{
				//return pCrawl; //changed from return as false to return being pcrawl -AS
				possibleDictionaryEntries = false; //new line added -AS
				Object[] dictionaryDescription = new Object[2]; //new line added -AS
				dictionaryDescription[0] = possibleDictionaryEntries; //new line added -AS
				dictionaryDescription[1] = pCrawl; //new line added -AS
				return dictionaryDescription; //new line added -AS
			}
			pCrawl = pCrawl.children[index]; 
		}
		possibleDictionaryEntries = pCrawl != null;
		Object[] dictionaryDescription2 = new Object[2]; //new line added -AS
		dictionaryDescription2[0] = possibleDictionaryEntries; //new line added -AS
		dictionaryDescription2[1] = pCrawl; //new line added -AS
		return dictionaryDescription2; //new line added -AS

		//return (pCrawl != null && pCrawl.isEndOfWord); //line not used -AS
	}
    //Deleted the main method -AS
}

