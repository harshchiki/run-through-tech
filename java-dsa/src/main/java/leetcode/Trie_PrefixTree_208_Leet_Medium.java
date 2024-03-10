package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class Trie_PrefixTree_208_Leet_Medium {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] sArr = { "the", "sky", "is", "blue", "their", "skyline", "isle", "bluefish" };
        for(String s : sArr) {
            trie.insert(s);
        }
        for(String s : sArr) {
            System.out.println("Searching for \"" + s + "\" " + trie.search(s));
        }
        System.out.println("harsh: " + trie.search("harsh"));
        System.out.println("skyli search : " + trie.search("skyli"));
        System.out.println("skyli startswith: " + trie.startsWith("skyli"));
        for(String s : sArr) {
            System.out.println("Prefix Searching for \"" + s + "\" " + trie.startsWith(s));
        }


    }

    static class Trie {
        Node root;
        public Trie() {
            root = new Node(true);
        }

        public void insert(String word) {
            Node thisNode = root;
            for(char c : word.toCharArray()) {
                Node possibleNode = new Node(c);
                Character key = Character.valueOf(c);
                if(!thisNode.children.containsKey(key)) {
                    thisNode.children.put(key, possibleNode);
                    thisNode = possibleNode;
                } else {
                    thisNode = thisNode.children.get(key);
                }
            }
            thisNode.isWordEnd = true;
        }

        public boolean search(String word) {
            Node thisNode = root;
            for(int i = 0 ; i<word.length();i++) {
                Character c = Character.valueOf(word.charAt(i));
                if(thisNode.children.containsKey(Character.valueOf(c))) {
                    thisNode = thisNode.children.get(Character.valueOf(c));
                } else {
                    return false;
                }
            }


            return thisNode.isWordEnd;
        }

        public boolean startsWith(String prefix) {
            Node thisNode = root;
            for(int i = 0 ; i<prefix.length();i++) {
                Character c = Character.valueOf(prefix.charAt(i));
                if(thisNode.children.containsKey(Character.valueOf(c))) {
                    thisNode = thisNode.children.get(Character.valueOf(c));
                } else {
                    return false;
                }
            }

            return true;
        }

        public void printTrie(Node node) {
            System.out.println("Node: " + node.thisChar);
            node.children.entrySet().forEach(entry -> {
                System.out.println(entry.getKey());
                printTrie(entry.getValue());
            });
        }

        class Node {
            char thisChar;
            Map<Character, Node> children;
            boolean isRoot = false;
            boolean isWordEnd = false;

            Node(char c) {
                thisChar = c;
                children = new HashMap<>();
            }

            Node(boolean isRoot) {
                thisChar = ' ';
                isRoot = true;
                children = new HashMap<>();
            }

            void addChild(Node n) {
                children.put(Character.valueOf(n.thisChar), n);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return thisChar == node.thisChar;
            }

            @Override
            public int hashCode() {
                return Objects.hash(thisChar);
            }
        }
    }
}
