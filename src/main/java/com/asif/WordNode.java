package com.asif;

import java.util.ArrayList;
import java.util.List;

/*
 * Class representing digital search tree (Trie) node for storing words
 * and their definitions. Assumes all lowercase alphabetic characters.
 */
class WordNode {
	private WordNode[] children = new WordNode[Util.NO_OF_CHARACTERS]; // pointers for 26 lowercase letters
	private String wordDefinition;
	private boolean endOfWord;

	public void addWord(String word, int start, String definition) {
		if (word == null || word.length() == 0) {
			return;
		}
		char c = word.charAt(start);
		int index = (int) c - Util.ASCII_BASE_STARTING_CHAR;
		WordNode node = children[index];
		if (node == null) {
			node = new WordNode();
			children[index] = node;
		}
		if (start < word.length() - 1) {
			node.addWord(word, start + 1, definition);
		} else {
			// this is the end of the word
			// set the end flag and definition here
			node.setEndOfWord(true);
			node.setWordDefinition(definition);
		}
	}

	public String getWordDefinition(String word, int start) {
		if (word == null || word.length() == 0) {
			return null;
		}

		char c = word.charAt(start);

		int index = (int) c - Util.ASCII_BASE_STARTING_CHAR;
		WordNode node = children[index];
		if (node == null) {
			return null;
		}
		if (start < word.length() - 1) {
			return node.getWordDefinition(word, start + 1);
		} else {
			// if end of word, then return definition
			// otherwise the word exists as prefix but not as a word, so return null
			return node.getEndOfWord() ? node.getWordDefinition() : null;
		}
	}
	
	public void deleteWord(String word, int start) {
		if (word == null || word.length() == 0) {
			return;
		}

		char c = word.charAt(start);

		int index = (int) c - Util.ASCII_BASE_STARTING_CHAR;
		WordNode node = children[index];
		if (node == null) {
			// word doesn't exist
			return;
		}
		if (start < word.length() - 1) {
			node.deleteWord(word, start + 1);
		} else {
			if(node.getEndOfWord()) {
				// set end flag as false and delete definition
				node.setEndOfWord(false);
				node.setWordDefinition(null);
			}
		}
	}

	public List<String> getWordsWithPrefix(String prefix) {
		if(prefix == null) {
			return Util.EMPTY_LIST;
		}
		if(prefix.length() > 1) {
			return getWordsWithPrefix(prefix, 0);			
		} else {
			// prefix empty, get all words from the trie.
			List<String> words = new ArrayList<String>();
			loadAllWordSuffixes(new StringBuilder(), words); 
			return words;
		}
	}
	
	private List<String> getWordsWithPrefix(String prefix, int start) {
		if (prefix == null || prefix.length() == 0) {
			return Util.EMPTY_LIST;
		}

		char c = prefix.charAt(start);

		int index = (int) c - Util.ASCII_BASE_STARTING_CHAR;
		WordNode node = children[index];
		if (node == null) {
			return Util.EMPTY_LIST;
		}
		if (start < prefix.length() - 1) {
			return node.getWordsWithPrefix(prefix, start + 1);
		} else {
			// got to the ending node of the prefix
			List<String> words = new ArrayList<String>();
			if(node.getEndOfWord()) {
				words.add(prefix);
			}
			// Now get all the suffixes and add them to the prefix
			// to build up the list
			List<String> suffixes = new ArrayList<String>();
			node.loadAllWordSuffixes(new StringBuilder(), suffixes); 
			
			for(String suffix : suffixes) {
				words.add(prefix + suffix);
			}
			
			return words;
		}
	}
	
	private void loadAllWordSuffixes(StringBuilder sb, List<String> suffixes) {
		for(int i = 0; i < Util.NO_OF_CHARACTERS; i++) {
			WordNode node = children[i];
			if(node != null) {
				char c = (char) (i + Util.ASCII_BASE_STARTING_CHAR);
				sb.append(c);
				if(node.getEndOfWord()) {
					suffixes.add(sb.toString());
				} 
				node.loadAllWordSuffixes(sb, suffixes);
				sb.setLength(sb.length() - 1);				
			}
		}
	}

	public void setEndOfWord(boolean endOfWord) {
		this.endOfWord = endOfWord;
	}

	public boolean getEndOfWord() {
		return endOfWord;
	}

	public String getWordDefinition() {
		return wordDefinition;
	}

	public void setWordDefinition(String wordDefinition) {
		this.wordDefinition = wordDefinition;
	}
}
