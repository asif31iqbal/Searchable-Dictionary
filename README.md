# Searchable-Dictionary
Searchable Dictionary that allows prefix search

Functionality:

* Inserting a word and its definition

* Retrieving all words that start with a given prefix

* Retrieving the definition of a given word

* Deleting a word from the dictionary

Assumptions:

* Assumes only alphabetic characters (but can be easily extended to a bigger set)
* Stores words as lowercase

Solution:

* The underlying data structure is actually a digital search tree (Trie) that allows prefix search.

Limitation of the initial solution:

* Doesn't handle exceptions/errors properly yet (however that can be added with little effort)
* Test cases not implemented yet
