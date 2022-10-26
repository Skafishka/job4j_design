package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final House house = new House(true, 1965, new Seller("Oleg"), new String[] {"Ivan, Anna, Kristina"});

        final Gson gson = new GsonBuilder().create();
        String rsl = gson.toJson(house);
        System.out.println(rsl);

        final House houseMod = gson.fromJson(rsl, House.class);
        System.out.println(houseMod);
    }
}
