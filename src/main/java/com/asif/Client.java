package com.asif;

import java.util.List;

public class Client {
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.addWord("asif", "software engineer");
		dict.addWord("asifiqbal", "software engineer 2");
		dict.addWord("asi", "soft engg");
		
		System.out.println(dict.getDefinition(""));
		List<String> list = dict.getWordsWithPrefix("A");
		System.out.println(list.size());
		dict.deleteWord("asif");
		list = dict.getWordsWithPrefix("A");
		System.out.println(list.size());
	}
}
