package com.mygdx.game.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Boufle on 02/11/2015.
 */
public class MenuShadowAnimation {

    Texture buffer;
    TextureRegion[] shadowAnimation;

    public MenuShadowAnimation(){

        buffer = new Texture("spirits\\MenuShadowAnimation24fps.png");
    }

    public TextureRegion[] BlitteringShadow(){

        TextureRegion[][] tmp = TextureRegion.split(buffer, buffer.getWidth() / 12, buffer.getHeight() /2);
        shadowAnimation = new TextureRegion[24];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 12; j++) {
                shadowAnimation[index++] = tmp[i][j];
            }
        }

        return shadowAnimation;
    }


}
