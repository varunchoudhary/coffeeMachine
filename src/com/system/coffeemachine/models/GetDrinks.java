package com.system.coffeemachine.models;

import com.system.coffeemachine.exceptions.NoIngredientsFoundException;

import java.util.Map;

public class GetDrinks extends Drink {

    public GetDrinks(Long milk, Long water, Long teaSyrup, Long gingerSyrup, Long sugarSyrup, Long elaichiSyrup, Long coffeeSyrup) {
        super(milk, water, teaSyrup, gingerSyrup, sugarSyrup, elaichiSyrup, coffeeSyrup);
    }


    @Override
    public boolean validate(Map<String, Long> ingredients) {
        String message = "";

        if(this.water > ingredients.get("water")) message = ingredients.get("water") <=0 ? "water is not available." : "water is not sufficient.";
        if(this.milk > ingredients.get("milk")) message = ingredients.get("milk") <=0 ? "milk is not available." : "milk is not sufficient.";
        if(this.teaSyrup > ingredients.get("teaSyrup")) message = ingredients.get("teaSyrup") <=0 ? "teaSyrup is not available." : "teaSyrup is not sufficient.";
        if(this.gingerSyrup > ingredients.get("gingerSyrup")) message = ingredients.get("gingerSyrup") <=0 ? "gingerSyrup is not available." : "gingerSyrup is not sufficient.";
        if(this.sugarSyrup > ingredients.get("sugarSyrup")) message = ingredients.get("sugarSyrup") <=0 ? "sugarSyrup is not available." : "sugarSyrup is not sufficient.";
        if(this.elaichiSyrup > ingredients.get("elaichiSyrup")) message = ingredients.get("elaichiSyrup") <=0 ? "elaichiSyrup is not available." : "elaichiSyrup is not sufficient.";
        if(this.coffeeSyrup > ingredients.get("coffeeSyrup")) message = ingredients.get("coffeeSyrup") <=0 ? "coffeeSyrup is not available." : "coffeeSyrup is not sufficient.";
        if(!message.equals(""))
            throw new NoIngredientsFoundException(message );
        return true;
    }

    @Override
    public Map<String, Long> ReduceIngredients(Map<String, Long> ingredients) {
        ingredients.put("water",ingredients.get("water")- this.water);
        ingredients.put("milk",ingredients.get("milk")- this.milk);
        ingredients.put("teaSyrup",ingredients.get("teaSyrup")- this.teaSyrup);
        ingredients.put("gingerSyrup",ingredients.get("gingerSyrup")- this.gingerSyrup);
        ingredients.put("sugarSyrup",ingredients.get("sugarSyrup")- this.sugarSyrup);
        ingredients.put("elaichiSyrup",ingredients.get("elaichiSyrup")- this.elaichiSyrup);
        ingredients.put("coffeeSyrup",ingredients.get("coffeeSyrup")- this.coffeeSyrup);
        return ingredients;
    }
}
