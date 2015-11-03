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

    protected int x , y;

    Texture img;
    TextureRegion[] test;


    TextureRegion[] MoveRight = new TextureRegion[10];
    TextureRegion[] MoveDown = new TextureRegion[8];
    TextureRegion[] moveUp = new TextureRegion[8];
    TextureRegion[] Stay = new TextureRegion[1];
    TextureRegion[] MoveLeft = new TextureRegion[10];


    public Isaac(){

        x = 40;
        y = 500;
        img = new Texture("spirits\\IsaacMove.png");

    }

    public TextureRegion[] moveRight(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 16; i < test.length - 6; i++){
            MoveRight[index] = test[i];
            index++;
        }

        return MoveRight;

    }


    public TextureRegion[] moveDown(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 8; i < test.length - 16; i++){
            MoveDown[index] = test[i];
            index++;
        }

        return MoveDown;

    }

    public TextureRegion[] moveLeft(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 16; i < test.length - 6; i++){
            MoveLeft[index] = test[i];
            index++;
        }

        return MoveLeft;

    }

    public TextureRegion[] moveUp(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 8; i < test.length - 16; i++){
            moveUp[index] = test[i];
            index++;
        }

        return moveUp;

    }

    public TextureRegion[] stay (){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }

        index = 0;
        for (int i = 8; i < test.length - 23; i++){
            Stay[index] = test[i];
            index++;
        }

        return Stay;
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
