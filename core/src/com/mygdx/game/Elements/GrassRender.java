package com.mygdx.game.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * com.mygdx.game.Elements
 * Created by Theo on 08/11/2015 for MurderessMoon.
 */
public class GrassRender {

     private SpriteBatch batch=null;
    private BitmapFont font;

     private TextureRegion[] test=null;

    public GrassRender() {
        batch = new SpriteBatch();
        font = new BitmapFont();
font.getData().setScale(0.75f,0.75f);
        Texture img = new Texture("spirits\\rocks_basement.png");
        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 4, img.getHeight() / 8);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                test[index++] = tmp[i][j];
            }
        }

    }


    public  void render(  Grass grass) {
       batch.begin();
        if(grass.health>0){
            batch.draw(test[1],grass.getY() ,grass.getX(), 64 ,64);
        }else {
            batch.draw(test[3],grass.getY() ,grass.getX(), 64 ,64);
        }

        font.draw(batch, "X" + grass.getX() + " Y" + grass.getY() +"H"+grass.health , grass.getY() , grass.getX()+32);

        batch.end();
    }

}
