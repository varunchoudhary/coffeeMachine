package com.system.coffeemachine.exceptions;

public class NoIngredientsFoundException extends RuntimeException {
   public NoIngredientsFoundException(String msg){
       super(msg);
   }

}
