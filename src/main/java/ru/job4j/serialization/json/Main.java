package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final House house = new House(true, 1965, new Seller("Oleg"), new String[] {"Ivan, Anna, Kristina"});

        final Gson gson = new GsonBuilder().create();
        String rsl = gson.toJson(house);
        System.out.println(rsl);

        final House houseMod = gson.fromJson(rsl, House.class);
        System.out.println(houseMod);

        System.out.println(new JSONObject(rsl));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("empty: ", house.isEmpty());
        jsonObject.put("construction age: ", house.getConstructionAge());
        jsonObject.put("seller: ", house.getSeller());
        jsonObject.put("possible buyers:", house.getPossibleBuyers());
        System.out.println(jsonObject);
    }
}