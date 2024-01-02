package medium;

import java.util.Stack;

/**
 * 
 * CodingNinjas: Trie Delete Operation
 * 
 * Link:
 * https://www.codingninjas.com/studio/problems/trie-delete-operation_1062663
 * 
 */
public class P2_Trie_Delete_Operation {
    static class TrieNode {
        TrieNode children[] = new TrieNode[26];
        boolean isEnd;

        TrieNode() {
            isEnd = false;

            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    private static boolean containsKey(TrieNode node, char ch) {
        return node.children[ch - 'a'] != null;
    }

    private static void put(TrieNode node, char ch) {
        node.children[ch - 'a'] = new TrieNode();
    }

    private static TrieNode get(TrieNode node, char ch) {
        return node.children[ch - 'a'];
    }

    private static void setEnd(TrieNode node) {
        node.isEnd = true;
    }

    private static void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!containsKey(node, ch)) {
                put(node, ch);
            }
            node = get(node, ch);
        }
        setEnd(node);
    }

    private static boolean search(TrieNode root, String word) {
        TrieNode node = root;
        boolean isFound = true;
        for (char ch : word.toCharArray()) {
            if (!containsKey(node, ch)) {
                isFound = false;
                break;
            }
            node = get(node, ch);
        }
        return isFound && node.isEnd;
    }

    public static void main(String[] args) {
        String[] values = { "hello", "help", "help", "hel", "hel" };
        TrieNode root = new TrieNode();
        for (String value : values) {
            insert(root, value);
            System.out.println(value + " has been inserted!");
        }
        String[] findValues = { "help", "hel", "hell" };
        for (String value : findValues) {
            System.out.println("'" + value + "' has been found : " + search(root, value));
        }
        String[] deleteValues = { "help", "hel" };
        TrieNode modifiedRoot = null;
        for (String value : deleteValues) {
            modifiedRoot = deleteWord(root, value);
            System.out.println("'" + value + "' has been deleted");
        }
        for (String value : findValues) {
            System.out.println("'" + value + "' has been found : " + search(modifiedRoot, value));
        }
    }

    public static TrieNode deleteWord(TrieNode root, String word) {
        if (root == null || !search(root, word)) {
            return null;
        }
        TrieNode node = root;
        Stack<TrieNodePair> stack = new Stack<TrieNodePair>();
        for (char ch : word.toCharArray()) {
            node = get(node, ch);
            stack.add(new TrieNodePair(ch, node));
        }
        while (!stack.isEmpty()) {
            TrieNodePair currentPair = stack.pop();
            char currentChar = currentPair.ch;
            currentPair.node.children[currentChar - 'a'] = null;
        }
        return node;
    }

    static class TrieNodePair {
        char ch;
        TrieNode node;

        public TrieNodePair(char ch, TrieNode node) {
            this.ch = ch;
            this.node = node;
        }
    }
}
