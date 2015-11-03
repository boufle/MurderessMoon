package com.mygdx.game.Elements;

import java.io.Serializable;

/**
 * isaac.Elements
 * Created by Theo on 02/11/2015 for helloworld.
 */
public class Grass extends Elements implements Serializable {

    Integer health = 1;


    public Grass(Integer X, Integer Y) {
        super(X,Y);
    }

    @Override
    public String toString() {
        return "Grass{" +
                "health=" + health +","+super.toString()+
                '}';
    }
}
