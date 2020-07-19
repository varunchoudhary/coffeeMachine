package com.system.coffeemachine;

import com.system.coffeemachine.databases.DrinkManager;
import com.system.coffeemachine.exceptions.IngredientNotPresentException;
import com.system.coffeemachine.services.Dispensers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        // this manager will be responsible for handling all the tasks relasted to drinks
        DrinkManager drinkManager;
        // data wil be fetched from https://www.npoint.io/docs/eeb009613590aaae5d65

        URL url = new URL("https://api.npoint.io/eeb009613590aaae5d65/machine");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        String line = "";
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String l;
            while ((l = in.readLine()) != null) {
                line += l;
            }
        }
        JSONParser parse = new JSONParser();
        JSONObject jobj = (JSONObject) parse.parse(line);

        JSONObject totalItemsQuantityquantity = (JSONObject) jobj.get("total_items_quantity");
        drinkManager = new DrinkManager(
                (Long) totalItemsQuantityquantity.get("water"),
                (Long) totalItemsQuantityquantity.get("milk"),
                (Long) totalItemsQuantityquantity.get("tea_syrup"),
                (Long) totalItemsQuantityquantity.get("coffee_syrup"),
                (Long) totalItemsQuantityquantity.get("sugar_syrup"),
                (Long) totalItemsQuantityquantity.get("elaichi_syrup"),
                (Long) totalItemsQuantityquantity.get("ginger_syrup")
        );

        Long numberOfThreads = (Long) ((JSONObject)jobj.get("outlets")).get("count_n"); // Number of threads
        JSONObject beverages = (JSONObject)jobj.get("beverages");
        ArrayList<String> beveragesName = new ArrayList<String>(beverages.keySet());

        int counter = 0;

//        To thread to perform parallely process multiple orders
        Dispensers object = new Dispensers(numberOfThreads);
        while(counter<beveragesName.size()) {
            object.run(beveragesName.get(counter),(JSONObject) beverages.get(beveragesName.get(counter)), drinkManager);
            counter+=1;
        }

//        To refill ingredient one at a time
        try {
            drinkManager.reFillIngredient("water",Long.valueOf(400));
        } catch (IngredientNotPresentException e) {
            e.printStackTrace();
        }
        try {
            drinkManager.reFillIngredient("gingerSyrup",Long.valueOf(400));
        } catch (IngredientNotPresentException e) {
            e.printStackTrace();
        }
//        after update can be hot_tea prepared
        object.run(beveragesName.get(3),(JSONObject) beverages.get(beveragesName.get(3)), drinkManager);

    }
}
