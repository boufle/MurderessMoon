package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.Animation.MenuShadowAnimation;
import com.mygdx.game.Event.EventMenuListener;
import com.mygdx.game.Event.MyTextInputListener;
import com.mygdx.game.MurderessMoon;
import com.mygdx.game.Particules.RandomParticules;


/**
 * Created by Boufle on 02/11/2015.
 */
public class MenuScreen implements Screen {

    private   RandomParticules rndpart;
    private   BitmapFont fontmenu;
    private   BitmapFont font;
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

    Vector2 dim = new Vector2();
    public MenuScreen(MurderessMoon m){

        batch = new SpriteBatch();
        img = new Texture("spirits\\gamemenu2.png");
        img2 = new Texture("spirits\\menuoverlay.png");


        font = new BitmapFont();
         fontmenu = new BitmapFont( );

        fontmenu.setColor(Color.GRAY);
        TextureRegion[] tmpAnimation = menuShadowAnimation.BlitteringShadow();
        shadowAnimation = new Animation(0.100f,tmpAnimation);
        stateTime = 0f;
        dim.x = Gdx.graphics.getWidth();
        dim.y =Gdx.graphics.getHeight();

        murderessMoon = m;
        rndpart = new RandomParticules();





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
        //background
        batch.draw(img, 0, 0, dim.x ,dim.y );
        //batch.draw(test[1],0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        fontmenu.draw(batch, "APUYER SUR ESPACE " , 600, 400);
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 40);


        //shader




        batch.end();

        rndpart.render(delta);

        batch.begin();
        //overlay
        batch.draw(currentFrame,0,0,  dim.x ,dim.y);

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
