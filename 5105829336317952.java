import java.util.*;

public class Solution {

    public static void main(String[] args) {
        assert longestSequenceLength(mkCircularLinkedList(1, 2, 1, 3, 4, 5)) == 5;
        assert longestSequenceLength(mkCircularLinkedList(1, 3, 4, 5, 1, 2)) == 5;
        assert longestSequenceLength(mkCircularLinkedList(1, 2, 3)) == 1;
        assert longestSequenceLength(mkCircularLinkedList(5)) == 1;
        assert longestSequenceLength(mkCircularLinkedList()) == 0;
    }

    private static Node mkCircularLinkedList(int... values) {
        if (values.length == 0) {
            return null;
        }
        Node first = new Node();
        Node last = first;
        for (int value : values) {
            last.next = new Node();
            last.next.value = value;
            last = last.next;
        }
        first = first.next;
        last.next = first;
        return first;
    }

    static class Node {
        int value;
        Node next;
    }

    /**
     * Find the length of the longest sequence of values of a circular linked list that starts and ends on
     * nodes that have the same value. Sequences that contain at least one node several times are not taken into
     * account (sequences with loops that can be infinite in size).
     */
    public static int longestSequenceLength(final Node firstNode) {
        if (firstNode == null) {
            return 0; // Consider it as an empty sequence (NullPointerException is also a valid choice)
        }
        Map<Integer, List<Integer>> valueIndexesMap = new HashMap<>();
        int index = 0;
        Node node = firstNode;
        do {
            int value = node.value;
            if (!valueIndexesMap.containsKey(value)) {
                valueIndexesMap.put(value, new ArrayList<>());
            }
            valueIndexesMap.get(value).add(index);
            index++;
            node = node.next;
        } while (node != firstNode);
        int size = index;
        int longestSequenceLength = 1;
        for (List<Integer> indexes : valueIndexesMap.values()) {
            if (indexes.size() > 1) {
                for (int i = 0; i < indexes.size(); i++) {
                    int j = (i + 1) % indexes.size();
                    int delta = indexes.get(j) - indexes.get(i);
                    if (delta < 0) {
                        delta += size;
                    }
                    int length = size - delta + 1;
                    if (length > longestSequenceLength) {
                        longestSequenceLength = length;
                    }
                }
            }
        }
        return longestSequenceLength;
    }
}
