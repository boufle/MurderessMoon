package com.mygdx.game.Elements;

import com.badlogic.gdx.math.Rectangle;

import java.io.Serializable;

/**
 * isaac.Elements
 * Created by Theo on 02/11/2015 for helloworld.
 */
public abstract class Elements implements Serializable{

    protected int Y;
    protected int X;
    private boolean bounds;

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

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    public abstract void render(float delta);

    public Rectangle getBounds() {
        return new Rectangle(X, Y, 64, 64);
    }

    public abstract void hit(int i);

    public abstract int getheal();
}
