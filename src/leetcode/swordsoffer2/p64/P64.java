package leetcode.swordsoffer2.p64;

import java.util.ArrayList;
import java.util.List;

public class P64 {

    /**
     * Your MagicDictionary object will be instantiated and called as such:
     * MagicDictionary obj = new MagicDictionary();
     * obj.buildDict(dictionary);
     * boolean param_2 = obj.search(searchWord);
     */
    public static void main(String[] args) {
        Testcase[] testcase = new Testcase[]{

                new Testcase(
                        new String[]{"hello","hallo","leetcode"},
                        new String[]{"hello", "hhllo", "hell", "leetcoded","leetxode","leetcode"},
                        new boolean[]{true, true, false, false,true,false}
                ),
                new Testcase(
                        new String[]{"hello", "leetcode"},
                        new String[]{"hello", "hhllo", "hell", "leetcoded","leetxode","leetcode"},
                        new boolean[]{false, true, false, false,true,false}
                ),

        };
        for (Testcase testcase1 : testcase) {
            System.out.println("#########");
            MagicDictionary obj = new MagicDictionary();

            testcase1.check(obj);
        }
    }
}

class MagicDictionary {

    Trie forward;
    Trie backword;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
        forward = new Trie();
        backword = new Trie();

    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            forward.insert(s);
        }

    }

    public boolean search(String searchWord) {
        List<String> forwardRet = forward.search(searchWord);
        for (String s : forwardRet) {
            if (s.length() == (searchWord.length())) {
                // 对比是否只有一个不一样
                int diff = 0;
                for (int i = 0; diff == 0 && i < s.length(); i++) {
                    if (s.charAt(i) != searchWord.charAt(i)) {
                        ++diff;
                    }
                }
                if (diff == 1) {
                    return true;
                }
            }
        }
        return false;

    }
}

class Trie {
    Trie[] children;
    boolean end = false;
    ArrayList<String> s;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        children = new Trie[26];
        s = new ArrayList<>();

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie p = this;
        int len = word.length();

        for (int i = 0; i < len; ++i) {
            p.s.add(word);
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new Trie();
            }
            p = p.children[idx];
        }
        p.end = true;
        // p.s = ;
        p.s.add(word);

    }

    public void reverseInsert(String word) {
        Trie p = this;
        int len = word.length();

        for (int i = len - 1; i >= 0; i--) {
            p.s.add(word);

            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                p.children[idx] = new Trie();
            }
            p = p.children[idx];
        }
        p.end = true;
        p.s.add(word);

        // p.s = word;
    }


    /**
     * Returns if the word is in the trie.
     */
    public List<String> search(String word) {
        Trie p = this;
        int len = word.length();
        for (int i = 0; i < len; ++i) {
            int idx = word.charAt(i) - 'a';
            if (p.children[idx] == null) {
                // return  p.s;
                // return false;
                break;
            }
            p = p.children[idx];
        }
        return p.s;
    }


}

class Testcase {
    String[] dictionary;
    String[] searchWords;
    boolean[] results;


    public Testcase(String[] dictionary, String[] searchWords, boolean[] results) {
        this.dictionary = dictionary;
        this.searchWords = searchWords;
        this.results = results;
    }

    public void check(MagicDictionary magicDictionary) {
        magicDictionary.buildDict(dictionary);
        for (int i = 0; i < searchWords.length; i++) {
            boolean ret = magicDictionary.search(searchWords[i]);
            System.out.printf("Searching:%s got %s , should be  %s %n", searchWords[i], ret, results[i]);
        }
    }
}
