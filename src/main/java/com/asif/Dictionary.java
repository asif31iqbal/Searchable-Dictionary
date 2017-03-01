package com.asif;

import java.util.List;

public class Dictionary {
	
    private WordNode root = new WordNode();
    
    /*
     * Adds the given word and its definition
     */
    public void addWord(String word, String definition) {
    	word = word.trim().toLowerCase();
        root.addWord(word, 0, definition);
    }
    
    /*
     * Delete given word
     */
    public void deleteWord(String word) {
    	word = word.trim().toLowerCase();
    	root.deleteWord(word, 0);
    }

    /*
     * Returns the definition of the word
     * Null if word not present
     */
    public String getDefinition(String word) {
    	word = word.trim().toLowerCase();
        return root.getWordDefinition(word, 0);
    }
    
    /*
     * Returns all words with the given prefix
     * Empty list if prefix not present
     */
    public List<String> getWordsWithPrefix(String prefix) {    	
    	prefix = prefix.trim().toLowerCase();
    	return root.getWordsWithPrefix(prefix);
    }
}
