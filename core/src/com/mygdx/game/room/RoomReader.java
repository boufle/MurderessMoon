package com.mygdx.game.room;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Donjon.Donjon;

import java.io.Serializable;

/**
 * com.mygdx.game.room
 * Created by Theo on 06/11/2015 for MurderessMoon.
 */
public class RoomReader implements Screen {


    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;

    public RoomReader(Donjon dj) {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, (w / h) *8.2f, 9.7f);
        camera.update();
        map = new TmxMapLoader().load("Room/Room1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1f / 32f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        renderer.setView(camera);
        renderer.render();
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
