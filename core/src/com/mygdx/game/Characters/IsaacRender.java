package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Event.EventIsaacListener;
import com.mygdx.game.TearsRender;

/**
 * com.mygdx.game.Characters
 * Created by Theo on 06/11/2015 for MurderessMoon.
 */
public class IsaacRender implements Screen {
    private   TextureRegion[] heath;
    private  Animation HeadFrontAnimation;
    private   SpriteBatch batch;
    private   BitmapFont font;
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
    private Isaac isaac;
    EventIsaacListener keyboard = new EventIsaacListener();
    private TextureRegion currentFrametemp ;
    private TextureRegion currentFrameHeadFront;





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


        TextureRegion[] headFront = headFront();
        HeadFrontAnimation = new Animation(0.100f,headFront);


         heath = heath(new Texture("spirits\\ui_hearts.png"));




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
        currentFrameHeadFront = HeadFrontAnimation.getKeyFrame(stateTime,true);
        //currentFrampLeft = MoveLeftAnimation.getKeyFrame(stateTime,true);
        TextureRegion todraw =currentFrameStay;
        TextureRegion todraw2 =HeadFrontAnimation.getKeyFrames()[0];
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

        if(isaac.attSpeedTemp !=0) {

            todraw2 =HeadFrontAnimation.getKeyFrames()[1];

            isaac.attSpeedTemp --;
        }


        if(keyboard.isShootgRight  && isaac.attSpeedTemp <=0) {
            isaac.shoot(new Vector2(1,0));
            //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        }
        if(keyboard.isShootDown  && isaac.attSpeedTemp <=0) {
            isaac.shoot(new Vector2(0,-1));
            todraw2 =HeadFrontAnimation.getKeyFrames()[1];
            //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        } if(keyboard.isShootLeft  && isaac.attSpeedTemp <=0) {
            isaac.shoot(new Vector2(-1,0));
            //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        }
        if(keyboard.isShootUp  && isaac.attSpeedTemp <=0) {
            isaac.hurt(1);
            isaac.shoot(new Vector2(0,1));
            //batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        }

        if(isaac.heathResist !=0) {
            isaac.heathResist --;

        }
        if (isaac.heathResist % 4 == 0) {
            batch.draw(todraw,isaac.getX(),isaac.getY(), 64 ,64);

            batch.draw(  todraw2   ,isaac.getX(),isaac.getY()+18, 64 ,64);



        }
        for (int i = 1; i <= isaac.heath; i+=2) {
            if(isaac.currentheath>i){
                batch.draw(  heath[0]   ,64+ ( i*34),650, 64 ,64);
            }else if(isaac.currentheath<i){
                batch.draw(  heath[2]   ,64+ ( i*34),650, 64 ,64);
            }else {

                batch.draw(  heath[1]   ,64+ ( i*34),650, 64 ,64);
            }
        }


        batch.end();

        batch.begin();
        font.draw(batch,  " X" + isaac.getX() + " Y" + isaac.getY() + " AS " +isaac.attSpeedTemp + " H"+isaac.currentheath+"/"+isaac.heath + " R"+isaac.heathResist, 10, 80);

        font.draw(batch, "U " +keyboard.isRunningUp +" D "+ keyboard.isRunningDown +" R "+ keyboard.isRunningRight +" L "+ keyboard.isRunningLeft+" S "+keyboard.isStay  , 10, 40);
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
        TextureRegion[] test = new TextureRegion[4 * 8];
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

    public TextureRegion[] heath(Texture texture){

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


    public TextureRegion[] moveDown(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
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

    public TextureRegion[] headFront(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }
        index = 0;
        for (int i = 0; i < 2;i++){
            HeadFront[index] = test[i];
            index++;
        }

        return HeadFront;

    }





    public TextureRegion[] moveUp(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        TextureRegion[] test = new TextureRegion[4 * 8];
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
        TextureRegion[] test = new TextureRegion[4 * 8];
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
