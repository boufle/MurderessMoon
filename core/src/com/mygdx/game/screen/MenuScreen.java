package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.MenuShadowAnimation;
import com.mygdx.game.Characters.Isaac;
import com.mygdx.game.Event.EventMenuListener;
import com.mygdx.game.MurderessMoon;


/**
 * Created by Boufle on 02/11/2015.
 */
public class MenuScreen implements Screen {

    SpriteBatch batch;
    Texture img;
    Texture img2;
    TextureRegion[] test;
    MurderessMoon murderessMoon;
    Animation shadowAnimation;
    TextureRegion currentFrame;
    MenuShadowAnimation menuShadowAnimation = new MenuShadowAnimation();
    float stateTime;

    Texture stay;


    public MenuScreen(MurderessMoon m){

        batch = new SpriteBatch();
        img = new Texture("spirits\\gamemenu2.png");
        img2 = new Texture("spirits\\menuoverlay.png");
        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 1, img.getHeight() / 2);
        test = new TextureRegion[1 * 2];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 1; j++) {
                test[index++] = tmp[i][j];
            }
        }


        TextureRegion[] tmpAnimation = menuShadowAnimation.BlitteringShadow();
        shadowAnimation = new Animation(0.100f,tmpAnimation);
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
        currentFrame = shadowAnimation.getKeyFrame(stateTime, true);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //batch.draw(test[1],0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(currentFrame,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        Gdx.input.setInputProcessor(new EventMenuListener(this));
        /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            murderessMoon.setScreen(new Game1(murderessMoon));*/
        //Gdx.app.log("Menu", "Dans le menu rose");
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

    public MurderessMoon getMurderessMoon() {
        return murderessMoon;
    }
}
