package medium;

/**
 * 
 * CodingNinjas: Trie Implementation
 * Link:
 * https://www.codingninjas.com/studio/problems/trie-implementation_1062581
 * 
 */
class TrieNode {
    TrieNode children[] = new TrieNode[26];
    boolean isEnd;

    TrieNode() {
        this.isEnd = false;
        for (int i = 0; i < 26; i++)
            children[i] = null;
    }
}

class P1_Trie_Implementation_Insert_Search_StartsWith extends TrieNode {

    static TrieNode root = new TrieNode();

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

    // Abstract method for insertion
    public static void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!containsKey(node, ch)) {
                put(node, ch);
            }
            node = get(node, ch);
        }
        setEnd(node);
    }

    // Abstract method for searching
    public static boolean search(String word) {
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

    // Abstract method for startsWith
    public static boolean startsWith(String word) {
        TrieNode node = root;
        boolean isFound = true;
        for (char ch : word.toCharArray()) {
            if (!containsKey(node, ch)) {
                isFound = false;
                break;
            }
            node = get(node, ch);
        }
        return isFound;
    }

    public static void main(String[] args) {
        String[] values = { "hello", "help", "help", "hel", "hel" };
        for (String value : values) {
            insert(value);
            System.out.println(value + " has been inserted!");
        }
        String[] findValues = { "help", "hel", "hell" };
        for (String value : findValues) {
            System.out.println("'" + value + "' has been found : " + search(value));
        }

        String[] findStartWithValues = { "he", "hel", "help", "ho" };
        for (String value : findStartWithValues) {
            System.out.println("Prefix starting with '" + value + "' has been found : " + startsWith(value));
        }
    }
}
