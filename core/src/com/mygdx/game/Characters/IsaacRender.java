package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Event.EventIsaacListener;

/**
 * com.mygdx.game.Characters
 * Created by Theo on 06/11/2015 for MurderessMoon.
 */
public class IsaacRender implements Screen {
    private   SpriteBatch batch;
    private   BitmapFont font;
    Animation MoveRightAnimation;
    Animation MoveDownAnimation;
    Animation StayAnimation;
    Animation MoveUpAnimation;
    Animation MoveLeftAnimation;

    TextureRegion[] test;


    TextureRegion[] MoveRight = new TextureRegion[10];
    TextureRegion[] MoveDown = new TextureRegion[8];
    TextureRegion[] moveUp = new TextureRegion[8];
    TextureRegion[] Stay = new TextureRegion[1];
    TextureRegion[] MoveLeft = new TextureRegion[10];

    TextureRegion currentFrame;

    TextureRegion currentFrampLeft;
    TextureRegion currentFrameUp;
    Texture img;

    TextureRegion currentFrameDown;
    TextureRegion currentFrameStay;

    float stateTime;
    private Isaac isaac;
    EventIsaacListener keyboard = new EventIsaacListener();
    private TextureRegion currentFrametemp ;

    public IsaacRender(Isaac isaac) {
        this.isaac = isaac;
        batch = new SpriteBatch();
        img = new Texture("spirits\\IsaacMove.png");


        stateTime = 0f;
        TextureRegion[] moveRight = moveRight();
        MoveRightAnimation = new Animation(0.100f,moveRight);
        font = new BitmapFont();
        TextureRegion[] moveDown = moveDown();
        MoveDownAnimation = new Animation(0.100f,moveDown);

        TextureRegion[] stay = stay();
        StayAnimation = new Animation(0.100f,stay);

        TextureRegion[] moveUp = moveUp();
        MoveUpAnimation = new Animation(0.100f,moveUp);

        TextureRegion[] moveLeft = moveRight();
        MoveLeftAnimation = new Animation(0.100f,moveLeft);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();
        Gdx.input.setInputProcessor(keyboard);

        currentFrame = MoveRightAnimation.getKeyFrame(stateTime, true);
        currentFrameDown = MoveDownAnimation.getKeyFrame(stateTime, true);
        currentFrameStay = StayAnimation.getKeyFrame(stateTime,true);
        currentFrameUp = MoveUpAnimation.getKeyFrame(stateTime,true);
        //currentFrampLeft = MoveLeftAnimation.getKeyFrame(stateTime,true);
        TextureRegion todraw =currentFrameStay;
        batch.begin();

        if (keyboard.isStay ||  (keyboard.isRunningLeft&&keyboard.isRunningRight) || (keyboard.isRunningUp&&keyboard.isRunningDown)  ){
            todraw = currentFrameStay;
            // batch.draw(currentFrameStay,isaac.getX(),isaac.getY(), 64 ,64);
        }


        if(keyboard.isRunningRight  ) {
            isaac.setX(isaac.getX() + isaac.speed);
            isaac.setY(isaac.getY());
            if(keyboard.isRunningRight && !keyboard.isRunningLeft  && !keyboard.isRunningDown  && !keyboard.isRunningUp) {
                if(currentFrame.isFlipX()){

                    currentFrame.flip(true,false);
                }
                todraw = currentFrame;
               // batch.draw(currentFrame, isaac.getX(), isaac.getY(), 64, 64);
            }else {
              //  currentFrame.
            }
        }

        if(keyboard.isRunningLeft) {
            isaac.setX(isaac.getX()-isaac.speed);
            isaac.setY(isaac.getY());
          //  batch.draw(currentFrampLeft,isaac.getX(),isaac.getY(), 64 ,64);
            if(keyboard.isRunningLeft && !keyboard.isRunningRight  && !keyboard.isRunningDown  && !keyboard.isRunningUp) {
                if(!currentFrame.isFlipX()){

                    currentFrame.flip(true,false);
                }
                todraw = currentFrame;
              //  batch.draw(currentFrame,isaac.getX(),isaac.getY(), 64 ,64);
            }
        }



        if(keyboard.isRunningDown) {
            isaac.setX(isaac.getX());
            isaac.setY(isaac.getY() - isaac.speed);
            todraw = currentFrameDown;

            //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        }
        if(keyboard.isRunningUp) {
            isaac.setX(isaac.getX());
            isaac.setY(isaac.getY() + isaac.speed);
            todraw = currentFrameDown;
            //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        }


        batch.draw(todraw,isaac.getX(),isaac.getY(), 64 ,64);



        batch.end();

        batch.begin();
        font.draw(batch, "U " +keyboard.isRunningUp +" D "+ keyboard.isRunningDown +" R "+ keyboard.isRunningRight +" L "+ keyboard.isRunningLeft+" S "+keyboard.isStay + " X" + isaac.getX() + " Y" + isaac.getY(), 10, 40);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

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
}