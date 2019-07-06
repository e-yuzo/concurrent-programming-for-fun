package threads.task_09.ex_02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import javafx.util.Pair;

/**
 *
 * @author Yuzo Description: Implemente o problema do produtor/consumidor para
 * uma estrutura com os seguintes campos: numero, simbolo, naipe, que
 * representa uma carta de baralho. A implementac~ao deve possibilitar que
 * apos 10 cartas produzidas por dois produtores, outros dois consumidores
 * pegarão somente as maiores cartas. Os produtores somente devem produzir mais
 * cartas após os consumidores removerem 3 cartas cada um. As cartas
 * remanescentes podem continuar na estrutura. Use a ordenac~ao do baralho da
 * menor para maior: A, 2, ..., 10, Q, J, K.
 * ORDEM: IF (SUIT das cartas forem iguais) THEN (comparar o RANK delas).
 * SUIT tem prioridade ao determinar a ordem.
 */
public class Main {

    public static void main(String[] args) {
        int BOUND = 100;
        int N_PRODUCERS = 2;
        int N_CONSUMERS = 2;
        BlockingQueue<Pair<String, String>> queue = new PriorityBlockingQueue<>
        (BOUND, new CardComparator());

//      if each position reaches 3, then stop consuming, and wait for producers
        List<Integer> consumerism = new ArrayList<>(N_CONSUMERS); //this variable isn't being used anymore
        consumerism.add(0);
        consumerism.add(0);

        CardPool cp = new CardPool(queue, consumerism);

        for (int i = 1; i <= N_PRODUCERS; i++) {
            Thread producer = new Thread(new Producer(queue, cp));
            producer.setName("Stranger Things. Part " + i);
            producer.start();
        }

        for (int j = 1; j <= N_CONSUMERS; j++) {
            Thread consumer = new Thread(new Consumer(queue, consumerism, cp));
            consumer.setName("Lord of the Rings. Part " + j);
            consumer.start();
        }
    }
}

class CardComparator implements Comparator<Pair<String, String>> {

    @Override
    public int compare(Pair<String, String> card1, Pair<String, String> card2) {
        DeckOfCards doc = new DeckOfCards();
        int r = doc.compare(card1, card2);
        return r;
    }
    
}
