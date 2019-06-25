package threads.task_09.ex_02;

import java.util.Arrays;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author Yuzo
 */
public class DeckOfCards {

    String[] SUITS = {
        "Hearts", "Diamonds", "Clubs", "Spades"
    };

    String[] RANKS = {
        "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "Jack", "Queen", "King", "Ace"
    };

    public String getRandomRank() {
        return (String) getRandom(RANKS);
    }

    public String getRandomSuit() {
        return (String) getRandom(SUITS);
    }

    private Object getRandom(Object[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public int compare(Pair<String, String> card1, Pair<String, String> card2) {
        int card1Suit = Arrays.asList(SUITS).indexOf(card1.getKey());
        int card2Suit = Arrays.asList(SUITS).indexOf(card2.getKey());
        if (card1Suit < card2Suit) {
            return 1;
        } else if (card1Suit > card2Suit) {
            return -1;
        } else {
            int card1Rank = Arrays.asList(RANKS).indexOf(card1.getValue());
            int card2Rank = Arrays.asList(RANKS).indexOf(card2.getValue());
            if (card1Rank < card2Rank) {
                return 1;
            } else if (card1Rank > card2Rank) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    
}
