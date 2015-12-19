import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        assert HandValue.NOTHING == findHandValue(new int[] {1,2,8,9,10});
        assert HandValue.PAIR == findHandValue(new int[] {1,2,2,3,4});
        assert HandValue.TWO_PAIRS == findHandValue(new int[] {1,1,3,7,7});
        assert HandValue.THREE_OF_A_KIND == findHandValue(new int[] {8,8,8,12,14});
        assert HandValue.FULL_HOUSE == findHandValue(new int[] {1,1,3,3,3});
        assert HandValue.FOUR_OF_A_KIND == findHandValue(new int[] {1,5,5,5,5});
    }

    public static final int MAX_OCCURRENCES_OF_SINGLE_CARD = 4;

    public enum HandValue {
        NOTHING(5, 0, 0, 0),
        PAIR(3, 1, 0, 0),
        TWO_PAIRS(1, 2, 0, 0),
        THREE_OF_A_KIND(2, 0, 1, 0),
        FULL_HOUSE(0, 1, 1, 0),
        FOUR_OF_A_KIND(1, 0, 0, 1);

        private final int[] totalOccurrences;

        HandValue(int... totalOccurrences) {
            this.totalOccurrences = totalOccurrences;
        }

        public boolean matches(int[] occurrences) {
            return Arrays.equals(totalOccurrences, occurrences);
        }
    }

    public static HandValue findHandValue(int[] hand) {
        if (hand == null) {
            throw new NullPointerException("Hand is null");
        }
        if (hand.length != 5) {
            throw new IllegalArgumentException("Hand does not contain 5 cards");
        }

        Arrays.sort(hand);

        int[] totalOccurrences = new int[MAX_OCCURRENCES_OF_SINGLE_CARD];

        int currentCardOccurrences = 1;
        int i = 0;
        while (i < hand.length) {
            while (i + 1 < hand.length && hand[i] == hand[i + 1]) {
                i++;
                currentCardOccurrences++;
            }
            totalOccurrences[currentCardOccurrences - 1]++;
            i++;
            currentCardOccurrences = 1;
        }

        for (HandValue handValue : HandValue.values()) {
            if (handValue.matches(totalOccurrences)) {
                return handValue;
            }
        }

        throw new IllegalStateException("Unreachable code");
    }

}
