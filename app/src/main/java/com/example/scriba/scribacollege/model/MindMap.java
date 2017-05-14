package com.example.scriba.scribacollege.model;

import java.io.Serializable;

/**
 * @author Ian Cunningham
 */

public class MindMap implements Serializable {

    private static final long serialVersionUID = -7060210544600464481L;

    private String root;
    private String leafOne, leafTwo, leafThree, leafFour, leafFive, leafSix, leafSeven, leafEight;

    public MindMap(String root, String leafOne, String leafTwo, String leafThree, String leafFour, String leafFive, String leafSix, String leafSeven, String leafEight) {
        this.root = root;
        this.leafOne = leafOne;
        this.leafTwo = leafTwo;
        this.leafThree = leafThree;
        this.leafFour = leafFour;
        this.leafFive = leafFive;
        this.leafSix = leafSix;
        this.leafSeven = leafSeven;
        this.leafEight = leafEight;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getLeafOne() {
        return leafOne;
    }

    public void setLeafOne(String leafOne) {
        this.leafOne = leafOne;
    }

    public String getLeafTwo() {
        return leafTwo;
    }

    public void setLeafTwo(String leafTwo) {
        this.leafTwo = leafTwo;
    }

    public String getLeafThree() {
        return leafThree;
    }

    public void setLeafThree(String leafThree) {
        this.leafThree = leafThree;
    }

    public String getLeafFour() {
        return leafFour;
    }

    public void setLeafFour(String leafFour) {
        this.leafFour = leafFour;
    }

    public String getLeafFive() {
        return leafFive;
    }

    public void setLeafFive(String leafFive) {
        this.leafFive = leafFive;
    }

    public String getLeafSix() {
        return leafSix;
    }

    public void setLeafSix(String leafSix) {
        this.leafSix = leafSix;
    }

    public String getLeafSeven() {
        return leafSeven;
    }

    public void setLeafSeven(String leafSeven) {
        this.leafSeven = leafSeven;
    }

    public String getLeafEight() {
        return leafEight;
    }

    public void setLeafEight(String leafEight) {
        this.leafEight = leafEight;
    }
}
