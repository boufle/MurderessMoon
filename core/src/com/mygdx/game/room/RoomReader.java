package com.mygdx.game.room;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Donjon.Donjon;
import com.mygdx.game.Elements.Elements;
import com.mygdx.game.TearsRender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * com.mygdx.game.room
 * Created by Theo on 06/11/2015 for MurderessMoon.
 */
public class RoomReader implements Screen {

    ArrayList<TearsRender> tearsRenders = new ArrayList<TearsRender>();

    private ParticleEffect effect;
    private SpriteBatch batch;

    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Room rm ;

    public RoomReader(Donjon dj) {
        batch = new SpriteBatch();

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut12.p"), Gdx.files.internal("img"));
        effect.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        effect.start();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, (w / h) *8.2f, 9.7f);
        camera.update();

        rm = dj.getFloors().get(dj.getCurrent()).getRm(0,0);
        map = new TmxMapLoader().load(rm.getTmx());
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
        batch.begin();
        for (Elements elem : rm.elems) {
            elem.render(delta);
        }



        Iterator<TearsRender> iter = tearsRenders.iterator();
        while (iter.hasNext()) {
            TearsRender tearsRender = iter.next();
            for (Elements elem : rm.elems) {
                if(elem.getheal()>0 && elem.getBounds().overlaps(tearsRender.getBounds())){
                    elem.hit(1);
                    tearsRender.hit();
                }
            }



            if(!tearsRender.isdead){
                tearsRender.render(delta);
            }else {
                tearsRender.dispose();
                iter.remove();
            }
        }


        // effect.draw(batch, delta);
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
        batch.dispose();
        effect.dispose();
    }


    public ArrayList<TearsRender> getTearsRenders() {
        return tearsRenders;
    }
}
