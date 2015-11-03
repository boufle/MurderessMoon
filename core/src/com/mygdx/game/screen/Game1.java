package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Characters.Isaac;
import com.mygdx.game.Event.EventIsaacListener;
import com.mygdx.game.Event.EventMenuListener;
import com.mygdx.game.MurderessMoon;

/**
 * Created by Boufle on 02/11/2015.
 */
public class Game1 implements Screen {

    SpriteBatch batch;
    Isaac isaac = new Isaac();


    Animation MoveRightAnimation;
    Animation MoveDownAnimation;
    Animation StayAnimation;
    Animation MoveUpAnimation;
    Animation MoveLeftAnimation;

    MurderessMoon murderessMoon;

    TextureRegion currentFrampLeft;
    TextureRegion currentFrameUp;
    TextureRegion currentFrame;
    TextureRegion currentFrameDown;
    TextureRegion currentFrameStay;


    int velocity = 2;
    float stateTime;

    EventIsaacListener keyboard = new EventIsaacListener();

    public Game1 (MurderessMoon m){

        batch = new SpriteBatch();

        TextureRegion[] moveRight = isaac.moveRight();
        MoveRightAnimation = new Animation(0.100f,moveRight);

        TextureRegion[] moveDown = isaac.moveDown();
        MoveDownAnimation = new Animation(0.100f,moveDown);

        TextureRegion[] stay = isaac.stay();
        StayAnimation = new Animation(0.100f,stay);

        TextureRegion[] moveUp = isaac.moveUp();
        MoveUpAnimation = new Animation(0.100f,moveUp);

        TextureRegion[] moveLeft = isaac.moveLeft();
        MoveLeftAnimation = new Animation(0.100f,moveLeft);

        stateTime = 0f;
        murderessMoon = m;

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0.2f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        currentFrame = MoveRightAnimation.getKeyFrame(stateTime, true);
        currentFrameDown = MoveDownAnimation.getKeyFrame(stateTime, true);
        currentFrameStay = StayAnimation.getKeyFrame(stateTime,true);
        currentFrameUp = MoveUpAnimation.getKeyFrame(stateTime,true);
        currentFrampLeft = MoveLeftAnimation.getKeyFrame(stateTime,true);

        batch.begin();
        Gdx.input.setInputProcessor(keyboard);

        if(keyboard.isRunningRight) {
            isaac.setX(isaac.getX() + velocity);
            isaac.setY(isaac.getY());
            batch.draw(currentFrame, isaac.getX(), isaac.getY(), 64, 64);
        }

        if(keyboard.isRunningDown) {
            isaac.setX(isaac.getX());
            isaac.setY(isaac.getY() - velocity);
            batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        }
        if(keyboard.isRunningUp) {
            isaac.setX(isaac.getX());
            isaac.setY(isaac.getY() + velocity);
            batch.draw(currentFrameDown,isaac.getX(),isaac.getY(), 64 ,64);
        }
        if(keyboard.isRunningLeft) {
            isaac.setX(isaac.getX()-velocity);
            isaac.setY(isaac.getY());
            batch.draw(currentFrampLeft,isaac.getX(),isaac.getY(), 64 ,64);
        }
        if (keyboard.isStay){
            batch.draw(currentFrameStay,isaac.getX(),isaac.getY(), 64 ,64);
        }




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

    public SpriteBatch getBatch() {
        return batch;
    }

    public Isaac getIsaac() {
        return isaac;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }
}
