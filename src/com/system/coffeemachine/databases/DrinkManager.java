package com.system.coffeemachine.databases;

import com.system.coffeemachine.exceptions.IngredientNotPresentException;
import com.system.coffeemachine.exceptions.NoIngredientsFoundException;
import com.system.coffeemachine.models.Drink;
import com.system.coffeemachine.models.GetDrinks;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DrinkManager {
    public static Map<String,Long> ingredients ;
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public DrinkManager(
            Long water,
            Long milk,
            Long teaSyrup,
            Long coffeeSyrup,
            Long sugarSyrup,
            Long elaichiSyrup,
            Long gingerSyrup
    ){
        ingredients = new HashMap<>();
        ingredients.put("water",water);
        ingredients.put("milk",milk);
        ingredients.put("teaSyrup",teaSyrup);
        ingredients.put("coffeeSyrup",coffeeSyrup);
        ingredients.put("sugarSyrup",sugarSyrup);
        ingredients.put("elaichiSyrup",elaichiSyrup);
        ingredients.put("gingerSyrup",gingerSyrup);
    }

    public Drink getDrink(JSONObject drinkIngredients) throws NoIngredientsFoundException {
        reentrantReadWriteLock.writeLock().lock();
        try {

            Long water = (drinkIngredients.containsKey("hot_water")) ? (Long) drinkIngredients.get("hot_water") : 0;
            Long milk = (drinkIngredients.containsKey("hot_milk")) ? (Long) drinkIngredients.get("hot_milk") : 0;
            Long teaSyrup = (drinkIngredients.containsKey("tea_leaves_syrup")) ? (Long) drinkIngredients.get("tea_leaves_syrup") : 0;
            Long coffeeSyrup = (drinkIngredients.containsKey("coffee_syrup")) ? (Long) drinkIngredients.get("coffee_syrup") : 0;
            Long sugarSyrup = (drinkIngredients.containsKey("sugar_syrup")) ? (Long) drinkIngredients.get("sugar_syrup") : 0;
            Long elaichiSyrup = (drinkIngredients.containsKey("elaichi_syrup")) ? (Long) drinkIngredients.get("elaichi_syrup") : 0;
            Long gingerSyrup = (drinkIngredients.containsKey("ginger_syrup")) ? (Long) drinkIngredients.get("ginger_syrup") : 0;

            Drink drink = new GetDrinks(milk, water, teaSyrup, gingerSyrup, sugarSyrup, elaichiSyrup, coffeeSyrup);
            drink.validate(ingredients);
            ingredients = drink.ReduceIngredients(ingredients);
            return drink;
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
    public boolean reFillIngredient(String ingredient,Long amount) throws IngredientNotPresentException {
        if(!ingredients.containsKey(ingredient))
             throw new IngredientNotPresentException();
        ingredients.put(ingredient,Math.min(ingredients.get(ingredient),0)+amount);
        return true;
    }

    @Override
    public String toString() {
        return "DrinkManager{" +
                "ingredients=" + ingredients +
                '}';
    }
}
