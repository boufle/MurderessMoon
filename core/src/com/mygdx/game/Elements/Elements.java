package com.mygdx.game.Elements;

import java.io.Serializable;

/**
 * isaac.Elements
 * Created by Theo on 02/11/2015 for helloworld.
 */
public abstract class Elements implements Serializable{

    protected int Y;
    protected int X;

    public Elements(int y, int x) {
        Y = y;
        X = x;
    }

    @Override
    public String toString() {
        return "Elements{" +
                "Y=" + Y +
                ", X=" + X +
                '}';
    }
}
