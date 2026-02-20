import java.util.*;

class TrieAutocomplete {

    static class TrieNode {

        Map<Character, TrieNode> children = new HashMap<>();
        Map<String, Integer> freqMap = new HashMap<>();
        boolean isEnd = false;
    }

    TrieNode root = new TrieNode();

    public void insert(String word, int freq) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
            node.freqMap.put(word, freq);
        }
        node.isEnd = true;
    }

    public List<String> getTop5(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) return new ArrayList<>();
            node = node.children.get(c);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );

        for (var e : node.freqMap.entrySet()) {
            pq.offer(e);
            if (pq.size() > 5) pq.poll();
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) res.add(pq.poll().getKey());
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        TrieAutocomplete t = new TrieAutocomplete();
        t.insert("apple", 10);
        t.insert("app", 15);
        t.insert("apply", 7);
        t.insert("ape", 12);
        System.out.println(t.getTop5("ap"));
    }
}
