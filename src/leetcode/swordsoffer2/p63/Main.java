package leetcode.swordsoffer2.p63;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.replaceWords(Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery");
        System.out.println(s);
        s = solution.replaceWords(Arrays.asList("a", "b", "c"), "aadsfasf absbs bbab cadsfafs");
        System.out.println(s);

        s = solution.replaceWords(Arrays.asList("a", "aa", "aaa", "aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa");
        System.out.println(s);

        s = solution.replaceWords(Arrays.asList("a", "b", "c"), "aadsfasf absbs bbab cadsfafs");
        System.out.println(s);
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie dict = new Trie();
        for (String s : dictionary) {
            dict.insert(s);
        }

        // 遍历单词
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String prefix = dict.findPrefix(word);
            if (prefix == null) {
                prefix = word;
            }
            sb.append(prefix);
            sb.append(' ');
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

class Trie {
    Trie[] children;
    // int childrenCnt=0;
    boolean end = false;
    String s;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        children = new Trie[26];

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie p = this;
        int len = word.length();

        for (int i = 0; i < len; ++i) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new Trie();
            }
            p = p.children[idx];
        }
        p.end = true;
        p.s = word;

    }

    /**
     * Returns if the word has a prefix in the trie.
     */
    public String findPrefix(String word) {
        Trie p = this;
        int len = word.length();
        for (int i = 0; i < len; ++i) {
            if (p.s != null) {
                break;
            }
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                break;
            }
            p = p.children[idx];
        }
        return p.s;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie p = this;
        int len = word.length();
        for (int i = 0; i < len; ++i) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                return false;
            }
            p = p.children[idx];
        }
        return p.end;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String word) {
        Trie p = this;
        int len = word.length();
        for (int i = 0; i < len; ++i) {
            int idx = word.charAt(i) - 'a';

            if (p.children[idx] == null) {
                return false;
            }
            p = p.children[idx];
        }
        return true;

    }
}
