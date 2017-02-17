package com.prettymuch.blackjack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dealer extends Player{

    final public static int FACE = 13;
    final public static int SUITE = 4;
    final public static int BLACK_JACK = 21;

    private int dealIndex = 51;
    private int round = 1;

    private ArrayList<Card> cardSet = new ArrayList<>();

    public Dealer() throws IOException {
        super(0);

        DeckOfCards deckOfCards = new DeckOfCards();
        cardSet = deckOfCards.getDeck();
        System.out.println("A dealer set up the deck");
        shuffleDeck();
        System.out.println("the deck shuffled up");
        printDeck();
       
    }

    public void printDeck() {

        for (Card card : cardSet) {
            System.out.printf("%s %s", card.getSuite(), card.getFace());
            System.out.println("");
        }
    }

    public void shuffleDeck() {

        long seed = System.nanoTime();
        Collections.shuffle(cardSet, new Random(seed));
        dealIndex = 51;

    }

    public Card dealCard(int dealIndex) {

        Card card_dealt = cardSet.get(dealIndex);
        dealIndexDecrement();
        
        return card_dealt;
    }
    
    public Card getCard(int dealIndex){
    	
        Card card = dealCard(dealIndex);
        getHand().add(card);
        
        return card;
    }

    public boolean isCardAce(Card card) {

        if (card.getFace().equals("ace")) return true;

        else return false;
    }

    public boolean isBlackJack(ArrayList<Card> hand){

        int isBlackJack = 0;

        for(Card card : hand){
            isBlackJack += card.getValue();
        }

        if(isBlackJack == 21){
            return true;
        }else {
            return false;
        }

    }

    public ArrayList<Card> getCardSet() {
        return cardSet;
    }

    public int getDealIndex() {
        return dealIndex;
    }

    public int getRound() {
        return round;
    }

    public void setRound() {
        this.round++;
    }

    public void dealIndexDecrement() {
        dealIndex--;
    }

}
