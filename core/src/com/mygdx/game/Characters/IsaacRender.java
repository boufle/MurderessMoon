package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.Event.EventIsaacListener;
import com.mygdx.game.Network;
import com.mygdx.game.screen.Game1;

/**
 * com.mygdx.game.Characters
 * Created by Theo on 06/11/2015 for MurderessMoon.
 */
public class IsaacRender {
    private TextureRegion[] heath;
    private Animation HeadFrontAnimation;
    private SpriteBatch batch;
    private BitmapFont font;
    Animation MoveRightAnimation;
    Animation MoveDownAnimation;
    Animation StayAnimation;
    Animation MoveUpAnimation;


    TextureRegion[] MoveRight = new TextureRegion[10];
    TextureRegion[] MoveDown = new TextureRegion[8];
    TextureRegion[] moveUp = new TextureRegion[8];
    TextureRegion[] Stay = new TextureRegion[1];

    TextureRegion[] HeadFront = new TextureRegion[2];


    TextureRegion currentFrame;

    TextureRegion currentFrampLeft;
    TextureRegion currentFrameUp;
    Texture img;

    TextureRegion currentFrameDown;
    TextureRegion currentFrameStay;

    float stateTime;
    EventIsaacListener keyboard = new EventIsaacListener();
    private TextureRegion currentFrametemp;
    private TextureRegion currentFrameHeadFront;


    public IsaacRender() {
        batch = new SpriteBatch();
        img = new Texture("spirits\\IsaacMove.png");


        stateTime = 0f;
        TextureRegion[] moveRight = moveRight();
        MoveRightAnimation = new Animation(0.100f, moveRight);
        font = new BitmapFont();
        TextureRegion[] moveDown = moveDown();
        MoveDownAnimation = new Animation(0.100f, moveDown);

        TextureRegion[] stay = stay();
        StayAnimation = new Animation(0.100f, stay);

        TextureRegion[] moveUp = moveUp();
        MoveUpAnimation = new Animation(0.100f, moveUp);


        TextureRegion[] headFront = headFront();
        HeadFrontAnimation = new Animation(0.100f, headFront);


        heath = heath(new Texture("spirits\\ui_hearts.png"));


    }


    public void render(float delta, Isaac isaac, boolean owner, Client client, Game1 game1) {
        stateTime += Gdx.graphics.getDeltaTime();
        Gdx.input.setInputProcessor(keyboard);
        currentFrame = MoveRightAnimation.getKeyFrame(stateTime, true);
        currentFrameDown = MoveDownAnimation.getKeyFrame(stateTime, true);
        currentFrameStay = StayAnimation.getKeyFrame(stateTime, true);
        currentFrameUp = MoveUpAnimation.getKeyFrame(stateTime, true);
        currentFrameHeadFront = HeadFrontAnimation.getKeyFrame(stateTime, true);
        //currentFrampLeft = MoveLeftAnimation.getKeyFrame(stateTime,true);
        TextureRegion todraw = currentFrameStay;
        TextureRegion todraw2 = HeadFrontAnimation.getKeyFrames()[0];

        batch.begin();
        if (owner) {

            boolean havemove = false;

            if (keyboard.isStay || (keyboard.isRunningLeft && keyboard.isRunningRight) || (keyboard.isRunningUp && keyboard.isRunningDown)) {
              if(isaac.getDirection()!=0){
                  isaac.setDirection(0);
                  havemove = true;
              }

                // batch.draw(currentFrameStay,isaac.getX(),isaac.getY(), 64 ,64);
            }


            if (keyboard.isRunningRight) {
                isaac.setX(isaac.getX() + isaac.speed);
                isaac.setY(isaac.getY());
                havemove = true;
                if (keyboard.isRunningRight && !keyboard.isRunningLeft && !keyboard.isRunningDown && !keyboard.isRunningUp) {
                    isaac.setDirection(2);

                    // batch.draw(currentFrame, isaac.getX(), isaac.getY(), 64, 64);
                } else {
                    //  currentFrame.
                }
            }

            if (keyboard.isRunningLeft) {
                isaac.setX(isaac.getX() - isaac.speed);
                isaac.setY(isaac.getY());                havemove = true;

                //  batch.draw(currentFrampLeft,isaac.getX(),isaac.getY(), 64 ,64);
                if (keyboard.isRunningLeft && !keyboard.isRunningRight && !keyboard.isRunningDown && !keyboard.isRunningUp) {

                    isaac.setDirection(4);


                    //  batch.draw(currentFrame,isaac.getX(),isaac.getY(), 64 ,64);
                }
            }


            if (keyboard.isRunningDown) {
                isaac.setX(isaac.getX());
                isaac.setY(isaac.getY() - isaac.speed);                havemove = true;
                isaac.setDirection(1);

                //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
            }
            if (keyboard.isRunningUp) {
                isaac.setX(isaac.getX());
                isaac.setY(isaac.getY() + isaac.speed);                havemove = true;
                isaac.setDirection(1);
                 //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
            }

            if(havemove){
                Network.UpdateCharacter msg = new Network.UpdateCharacter();
                msg.x = isaac.getX();
                msg.y = isaac.getY();
                msg.direction = isaac.getDirection();
                client.sendTCP(msg);
            }

            if (isaac.attSpeedTemp != 0) {

                todraw2 = HeadFrontAnimation.getKeyFrames()[1];

                isaac.attSpeedTemp--;
            }


            if (keyboard.isShootgRight && isaac.attSpeedTemp <= 0) {
                isaac.shoot(new Vector2(1, 0),game1);
                //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
            }
            if (keyboard.isShootDown && isaac.attSpeedTemp <= 0) {
                isaac.shoot(new Vector2(0, -1), game1);
                todraw2 = HeadFrontAnimation.getKeyFrames()[1];
                //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
            }
            if (keyboard.isShootLeft && isaac.attSpeedTemp <= 0) {
                isaac.shoot(new Vector2(-1, 0), game1);
                //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
            }
            if (keyboard.isShootUp && isaac.attSpeedTemp <= 0) {
                isaac.hurt(1);
                isaac.shoot(new Vector2(0, 1), game1);
                //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
            }

            if (isaac.heathResist != 0) {
                isaac.heathResist--;

            }

            for (int i = 1; i <= isaac.heath; i += 2) {
                if (isaac.currentheath > i) {
                    batch.draw(heath[0], 64 + (i * 34), 650, 64, 64);
                } else if (isaac.currentheath < i) {
                    batch.draw(heath[2], 64 + (i * 34), 650, 64, 64);
                } else {

                    batch.draw(heath[1], 64 + (i * 34), 650, 64, 64);
                }
            }

            font.draw(batch, " X" + isaac.getX() + " Y" + isaac.getY() + " AS " + isaac.attSpeedTemp + " H" + isaac.currentheath + "/" + isaac.heath + " R" + isaac.heathResist, 10, 80);

            font.draw(batch, "U " + keyboard.isRunningUp + " D " + keyboard.isRunningDown + " R " + keyboard.isRunningRight + " L " + keyboard.isRunningLeft + " S " + keyboard.isStay, 10, 40);

        }
        if (isaac.heathResist % 4 == 0) {
            if(isaac.getDirection()==1){
                todraw = currentFrameDown;
            }else if(isaac.getDirection()==4){
                if (!currentFrame.isFlipX()) {    currentFrame.flip(true, false);       }
                todraw = currentFrame;
            }else if(isaac.getDirection()==2){
                if (currentFrame.isFlipX()) { currentFrame.flip(true, false);       }
                todraw = currentFrame;
            }else if (isaac.getDirection()==0){
                todraw = currentFrameStay;
            }

            batch.draw(todraw, isaac.getX(), isaac.getY(), 64, 64);

            batch.draw(todraw2, isaac.getX(), isaac.getY() + 18, 64, 64);

        }




       batch.end();

    }


    public TextureRegion[] moveRight() {

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 16; i < test.length - 6; i++) {
            MoveRight[index] = test[i];
            index++;
        }

        return MoveRight;

    }

    public TextureRegion[] heath(Texture texture) {

        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / 5, texture.getHeight() / 2);
        TextureRegion[] test = new TextureRegion[5 * 2];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                test[index++] = tmp[i][j];
            }
        }


        return test;

    }


    public TextureRegion[] moveDown() {

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 8; i < test.length - 16; i++) {
            MoveDown[index] = test[i];
            index++;
        }

        return MoveDown;

    }

    public TextureRegion[] headFront() {

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 0; i < 2; i++) {
            HeadFront[index] = test[i];
            index++;
        }

        return HeadFront;

    }


    public TextureRegion[] moveUp() {

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 8; i < test.length - 16; i++) {
            moveUp[index] = test[i];
            index++;
        }

        return moveUp;

    }

    public TextureRegion[] stay() {

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }

        index = 0;
        for (int i = 8; i < test.length - 23; i++) {
            Stay[index] = test[i];
            index++;
        }

        return Stay;
    }
}
