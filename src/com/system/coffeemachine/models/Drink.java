package com.system.coffeemachine.models;

import java.util.Map;

public abstract class Drink {
    protected Long milk;
    protected Long water;
    protected Long teaSyrup;
    protected Long gingerSyrup;
    protected Long sugarSyrup;
    protected Long elaichiSyrup;
    protected Long coffeeSyrup;

    public Drink(Long milk, Long water, Long teaSyrup, Long gingerSyrup, Long sugarSyrup, Long elaichiSyrup, Long coffeeSyrup) {
        this.milk = milk;
        this.water = water;
        this.teaSyrup = teaSyrup;
        this.gingerSyrup = gingerSyrup;
        this.sugarSyrup = sugarSyrup;
        this.elaichiSyrup = elaichiSyrup;
        this.coffeeSyrup = coffeeSyrup;
    }


    public Long getMilk() {
        return milk;
    }

    public Long getWater() {
        return water;
    }

    public Long getTeaSyrup() {
        return teaSyrup;
    }

    public Long getGingerSyrup() {
        return gingerSyrup;
    }

    public Long getSugarSyrup() {
        return sugarSyrup;
    }

    public Long getElaichiSyrup() {
        return elaichiSyrup;
    }

    public Long getCoffeeSyrup() {
        return coffeeSyrup;
    }

    public abstract boolean validate(Map<String,Long> ingredients );
    public abstract Map<String, Long> ReduceIngredients(Map<String,Long> ingredients );

    @Override
    public String toString() {
        return "Drink{" +
                "milk=" + milk +
                ", water=" + water +
                ", teaSyrup=" + teaSyrup +
                ", gingerSyrup=" + gingerSyrup +
                ", sugarSyrup=" + sugarSyrup +
                ", elaichiSyrup=" + elaichiSyrup +
                ", coffeeSyrup=" + coffeeSyrup +
                '}';
    }
}
