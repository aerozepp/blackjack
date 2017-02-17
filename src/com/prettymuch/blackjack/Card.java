package com.prettymuch.blackjack;

import java.awt.image.BufferedImage;

public class Card {

    private String face;
    private String suite;
    private int value;
    private String path;
    private BufferedImage img;


    public String getFace() {
        return face;
    }

    public String getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }

    public String getPath() {
        return path;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPath(String name) {
        this.path = name;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
