package com.prettymuch.blackjack;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.prettymuch.blackjack.Dealer.FACE;
import static com.prettymuch.blackjack.Dealer.SUITE;

public class DeckOfCards {

    private ArrayList<Card> deck;

    public DeckOfCards() throws IOException {

        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
        String[] suite = {"diamonds", "clubs", "hearts", "spades"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        deck = new ArrayList<Card>();

        for (int i = 0; i < SUITE; i++) {
            for(int j = 0; j < FACE ; j++){

                Card card = new Card();
                BufferedImage temp = null;

                card.setSuite(suite[i]);
                card.setFace(faces[j]);
                card.setValue(values[j]);
                card.setPath("src/img/" + faces[j] + "_of_" + suite[i] + ".png");

                try {
                    temp = ImageIO.read(new File(card.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                card.setImg(temp);
                deck.add(card);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
