package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import javafx.animation.Animation;

/**
 * Created by Boufle on 02/11/2015.
 */
public class Isaac {

    protected int x , y,speed=4;
    protected double damage = 1;
    protected int heath = 10;

    public Isaac(){
        x = 588;
        y = 364;
        new IsaacRender(this);

    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
