package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Animation.MenuShadowAnimation;
import com.mygdx.game.Characters.Isaac;
import com.mygdx.game.Characters.IsaacRender;
import com.mygdx.game.Donjon.Donjon;
import com.mygdx.game.Event.EventIsaacListener;
import com.mygdx.game.Event.EventMenuListener;
import com.mygdx.game.MurderessMoon;
import com.mygdx.game.TearsRender;
import com.mygdx.game.room.RoomReader;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Boufle on 02/11/2015.
 */
public class Game1 implements Screen {

    private   AssetLoader asset;
    private   RoomReader roomReader;
    private   Isaac isaac;
    SpriteBatch batch;

    public RoomReader getRoomReader() {
        return roomReader;
    }

    private BitmapFont font;
    float stateTime;



    public Game1(MurderessMoon murderessMoon, Donjon dj){

        batch = new SpriteBatch();

        asset = new AssetLoader(dj);

        stateTime = 0f;

        font = new BitmapFont();

        isaac =  new Isaac(this);
        roomReader = new RoomReader(dj);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0.2f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

       // asset.render();
        roomReader.render(delta);

        isaac.getRender(delta);


        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
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
}
