package com.system.coffeemachine.services;

import com.system.coffeemachine.databases.DrinkManager;
import com.system.coffeemachine.exceptions.NoIngredientsFoundException;
import com.system.coffeemachine.models.Drink;
import org.json.simple.JSONObject;

public class Dispensers extends Thread {
    private static final long DELAY = 100;
    public static Long numberOfThreads;
    public static Long threadCounter;

    public Dispensers(Long numberOfThreads){
        this.numberOfThreads = numberOfThreads;
        this.threadCounter = Long.valueOf(0);
    }

    public void run(String s, JSONObject ingredients, DrinkManager drinkManager)  {
        if(threadCounter >= numberOfThreads) {
            try {
                Thread.sleep(this.DELAY);
            } catch(InterruptedException e) {
                System.out.println("Thread error");
            }
            this.run();
            return;
        }
        threadCounter++;
        try
        {
            sleep(500);
            Drink drink = drinkManager.getDrink(ingredients);
            System.out.println(s+" is prepared.");
        }catch (NoIngredientsFoundException e) {
            System.out.println (s + " cannot be prepared because "+e.getMessage());
        }catch (Exception e) {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }finally {
            threadCounter--;
        }
    }
}