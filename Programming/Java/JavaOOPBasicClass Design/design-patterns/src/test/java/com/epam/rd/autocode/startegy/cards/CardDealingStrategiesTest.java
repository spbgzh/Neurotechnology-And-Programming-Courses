package com.epam.rd.autocode.startegy.cards;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class CardDealingStrategiesTest {


    @Test
    public void texasHoldemCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.texasHoldemCardDealingStrategy();

        testCase(strategy, 52, 3,
                "{Community=[45, 44, 43, 42, 41], " +
                        "Player 1=[51, 48], " +
                        "Player 2=[50, 47], " +
                        "Player 3=[49, 46], " +
                        "Remaining=[40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");

        testCase(strategy, 52, 4,
                "{Community=[43, 42, 41, 40, 39], " +
                        "Player 1=[51, 47], " +
                        "Player 2=[50, 46], " +
                        "Player 3=[49, 45], " +
                        "Player 4=[48, 44], " +
                        "Remaining=[38, 37, 36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");

        testCase(strategy, 36, 5,
                "{Community=[25, 24, 23, 22, 21], " +
                        "Player 1=[35, 30], " +
                        "Player 2=[34, 29], " +
                        "Player 3=[33, 28], " +
                        "Player 4=[32, 27], " +
                        "Player 5=[31, 26], " +
                        "Remaining=[20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
    }

    @Test
    public void classicPokerCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.classicPokerCardDealingStrategy();

        testCase(strategy, 52, 3,
                "{Player 1=[51, 48, 45, 42, 39], " +
                        "Player 2=[50, 47, 44, 41, 38], " +
                        "Player 3=[49, 46, 43, 40, 37], " +
                        "Remaining=[36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");

        testCase(strategy, 52, 4,
                "{Player 1=[51, 47, 43, 39, 35], " +
                        "Player 2=[50, 46, 42, 38, 34], " +
                        "Player 3=[49, 45, 41, 37, 33], " +
                        "Player 4=[48, 44, 40, 36, 32], " +
                        "Remaining=[31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");

        testCase(strategy, 36, 5,
                "{Player 1=[35, 30, 25, 20, 15], " +
                        "Player 2=[34, 29, 24, 19, 14], " +
                        "Player 3=[33, 28, 23, 18, 13], " +
                        "Player 4=[32, 27, 22, 17, 12], " +
                        "Player 5=[31, 26, 21, 16, 11], " +
                        "Remaining=[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]}");
    }

    @Test
    public void bridgeCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.bridgeCardDealingStrategy();

        testCase(strategy, 52, 4,
                "{Player 1=[51, 47, 43, 39, 35, 31, 27, 23, 19, 15, 11, 7, 3], " +
                        "Player 2=[50, 46, 42, 38, 34, 30, 26, 22, 18, 14, 10, 6, 2], " +
                        "Player 3=[49, 45, 41, 37, 33, 29, 25, 21, 17, 13, 9, 5, 1], " +
                        "Player 4=[48, 44, 40, 36, 32, 28, 24, 20, 16, 12, 8, 4, 0]}");
    }

    @Test
    public void foolCardDealingStrategy() {
        final CardDealingStrategy strategy = CardDealingStrategies.foolCardDealingStrategy();

        testCase(strategy, 52, 3,
                "{Player 1=[51, 48, 45, 42, 39, 36], " +
                        "Player 2=[50, 47, 44, 41, 38, 35], " +
                        "Player 3=[49, 46, 43, 40, 37, 34], " +
                        "Remaining=[32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], " +
                        "Trump card=[33]}");

        testCase(strategy, 52, 4,
                "{Player 1=[51, 47, 43, 39, 35, 31], " +
                        "Player 2=[50, 46, 42, 38, 34, 30], " +
                        "Player 3=[49, 45, 41, 37, 33, 29], " +
                        "Player 4=[48, 44, 40, 36, 32, 28], " +
                        "Remaining=[26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], " +
                        "Trump card=[27]}");

        testCase(strategy, 36, 5,
                "{Player 1=[35, 30, 25, 20, 15, 10], " +
                        "Player 2=[34, 29, 24, 19, 14, 9], " +
                        "Player 3=[33, 28, 23, 18, 13, 8], " +
                        "Player 4=[32, 27, 22, 17, 12, 7], " +
                        "Player 5=[31, 26, 21, 16, 11, 6], " +
                        "Remaining=[4, 3, 2, 1, 0], " +
                        "Trump card=[5]}");

        testCase(strategy, 36, 3,
                "{Player 1=[35, 32, 29, 26, 23, 20], " +
                        "Player 2=[34, 31, 28, 25, 22, 19], " +
                        "Player 3=[33, 30, 27, 24, 21, 18], " +
                        "Remaining=[16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0], " +
                        "Trump card=[17]}");
    }

    private void testCase(final CardDealingStrategy strategy, final int deckSize, final int players, final String expected) {
        final Deck deck = new DeckImpl(deckSize);
        assertEquals(deckSize, deck.size());
        assertEquals(
                expected,
                new TreeMap(strategy.dealStacks(deck, players)).toString()
        );
        assertEquals(0, deck.size());
    }


}

class CardImpl implements Card {

    private String name;

    CardImpl(final String name) {
        this.name = name;
    }

    CardImpl(final int name) {
        this(Integer.toString(name));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class DeckImpl implements Deck {

    LinkedList<Card> cards;

    public DeckImpl(final int cardsAmount) {
        this.cards = new LinkedList<>();
        for (int i = 0; i < cardsAmount; i++) {
            cards.push(new CardImpl(i));
        }
    }

    @Override
    public Card dealCard() {
        return cards.size() == 0 ? null : cards.pop();
    }

    @Override
    public List<Card> restCards() {
        final ArrayList<Card> rest = new ArrayList<>(this.cards);
        cards.clear();
        return rest;
    }

    @Override
    public int size() {
        return cards.size();
    }


}

