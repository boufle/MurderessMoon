package com.mygdx.game.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.io.Serializable;

/**
 * isaac.Elements
 * Created by Theo on 02/11/2015 for helloworld.
 */
public class Grass   implements Serializable {

    private   Integer x;
    private  Integer y;
    //private   SpriteBatch batch;
    Integer health = 2;
    //private TextureRegion[] test;


    public Grass(Integer X, Integer Y) {


       // batch = new SpriteBatch();

        //img = ;
       // Rock();
        x = X;
        y = Y;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + health;
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y, 64, 64);
    }

     public void hit(int i) {
        health--;
    }

     public int getheal() {
        return health;
    }


    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
