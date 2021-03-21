package com.epam.rd.autocode.startegy.cards;

import com.epam.rd.autocode.observer.git.Commit;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {


                Map<String, List<Card>> res = new TreeMap<>();
                List<Card> extr = new ArrayList<>();
                for(int y = 0; y < 5; y++) {
                    extr.add(new card(Integer.toString(deck.size() - 1 - players * 2 - y)));
                }
                res.put("Community", extr);
                int max = deck.size();
                for(int i = 0; i < players; i++) {
                    int el = --max;
                    List<Card> pli = new ArrayList<>();
                    for(int j = 0; j < 2; j++) { pli.add(new card(Integer.toString(el))); el -= players; }
                    res.put("Player " + Integer.toString(i + 1), pli);
                }
                List<Card> rem = new ArrayList<>();
                for(int k = deck.size() - 1 - players * 2 - 5; k >= 0; k--) {
                    rem.add(new card(Integer.toString(k)));
                }

                if(rem.size() != 0) res.put("Remaining", rem);
                deck.restCards();
                return res;



            }
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {


                Map<String, List<Card>> res = new TreeMap<>();
                List<Card> extr = new ArrayList<>();

                int max = deck.size();
                for(int i = 0; i < players; i++) {
                    int el = --max;
                    List<Card> pli = new ArrayList<>();
                    for(int j = 0; j < 5; j++) { pli.add(new card(Integer.toString(el))); el -= players; }
                    res.put("Player " + Integer.toString(i + 1), pli);
                }
                List<Card> rem = new ArrayList<>();
                for(int k = deck.size() - 1 - players * 5 ; k >= 0; k--) {
                    rem.add(new card(Integer.toString(k)));
                }

                if(rem.size() != 0) res.put("Remaining", rem);
                deck.restCards();
                return res;



            }
        };


    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {


                Map<String, List<Card>> res = new TreeMap<>();
                List<Card> extr = new ArrayList<>();

                int max = deck.size();
                for (int i = 0; i < players; i++) {
                    int el = --max;
                    List<Card> pli = new ArrayList<>();
                    for (int j = 0; j < 13; j++) {
                        pli.add(new card(Integer.toString(el)));
                        el -= players;
                    }
                    res.put("Player " + Integer.toString(i + 1), pli);
                }
                List<Card> rem = new ArrayList<>();
                for (int k = deck.size() - 1 - players * 13; k >= 0; k--) {
                    rem.add(new card(Integer.toString(k)));
                }

                if (rem.size() != 0) res.put("Player", rem);
                deck.restCards();
                return res;

            }
        };

    }

    public static CardDealingStrategy foolCardDealingStrategy() {

        return new CardDealingStrategy() {
            @Override
            public Map<String, List<Card>> dealStacks(Deck deck, int players) {


                Map<String, List<Card>> res = new TreeMap<>();
                List<Card> extr = new ArrayList<>();
                int max = deck.size();
                for (int i = 0; i < players; i++) {
                    int el = --max;
                    List<Card> pli = new ArrayList<>();
                    for (int j = 0; j < 6; j++) {
                        pli.add(new card(Integer.toString(el)));
                        el -= players;
                    }
                    res.put("Player " + Integer.toString(i + 1), pli);
                }
                List<Card> rem = new ArrayList<>();
                for (int k = deck.size() - 1 - players * 6 - 1 ; k >= 0; k--) {
                    rem.add(new card(Integer.toString(k)));
                }

                if (rem.size() != 0) res.put("Remaining", rem);
                List<Card> rem1 = new ArrayList<>();
                rem1.add(new card(Integer.toString(deck.size()  - players * 6 - 1 )));
                res.put("Trump card", rem1);
                deck.restCards();
                return res;


            }
        };
    }

}
