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
public class Grass extends Elements implements Serializable {

    private   Texture img;
    private   SpriteBatch batch;
    Integer health = 1;
    private TextureRegion[] test;


    public Grass(Integer X, Integer Y) {

        super(X,Y);
        batch = new SpriteBatch();

        img = new Texture("spirits\\rocks_basement.png");
        Rock();
    }

    @Override
    public String toString() {
        return "Grass{" +
                "health=" + health +","+super.toString()+
                '}';
    }

    public Rectangle getBounds() {
        return new Rectangle(X,Y, 64, 64);
    }

    @Override
    public void hit(int i) {
        health--;
    }

    @Override
    public int getheal() {
        return health;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        if(health>0){
            batch.draw(test[1],X,Y, 64 ,64);
        }else {
            batch.draw(test[3],X,Y, 64 ,64);

        }

        batch.end();

    }

    public  void Rock(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 4, img.getHeight() / 8);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                test[index++] = tmp[i][j];
            }
        }


    }
}
