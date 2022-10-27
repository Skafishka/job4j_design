package ru.job4j.serialization.json;

import java.util.Arrays;

public class House {
    private final boolean empty;
    private final int constructionAge;
    private final Seller seller;
    private final String[] possibleBuyers;

    public House(boolean empty, int constructionAge, Seller seller, String[] possibleBuyers) {
        this.empty = empty;
        this.constructionAge = constructionAge;
        this.seller = seller;
        this.possibleBuyers = possibleBuyers;
    }

    @Override
    public String toString() {
        return "House{"
                + "empty=" + empty
                + ", constructionAge=" + constructionAge
                + ", seller=" + seller
                + ", possibleBuyers=" + Arrays.toString(possibleBuyers)
                + '}';
    }

    public boolean isEmpty() {
        return empty;
    }

    public int getConstructionAge() {
        return constructionAge;
    }

    public Seller getSeller() {
        return seller;
    }

    public String[] getPossibleBuyers() {
        return possibleBuyers;
    }
}
