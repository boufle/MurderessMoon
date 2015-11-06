package com.mygdx.game.Donjon;

/**
 * isaac.Donjon
 * Created by Theo on 02/11/2015 for helloworld.
 */
public class Generation {
    public static Donjon Generer(Integer seed) {

        Donjon dj =  new Donjon(seed);

        return dj;
    }
   public static Donjon Generer() {


        int seed = 4000 + (int) (Math.random() * 600000);
        Donjon dj =  new Donjon(seed);
        return dj;
    }
}
