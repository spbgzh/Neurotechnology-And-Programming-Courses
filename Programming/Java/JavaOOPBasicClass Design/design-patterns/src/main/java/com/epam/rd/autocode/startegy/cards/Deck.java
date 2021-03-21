package com.epam.rd.autocode.startegy.cards;

import java.util.List;

public interface Deck{
    Card dealCard();
    List<Card> restCards();
    int size();
}
