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
import com.mygdx.game.Elements.Grass;
import com.mygdx.game.Elements.GrassRender;
import com.mygdx.game.TearsRender;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * com.mygdx.game.room
 * Created by Theo on 06/11/2015 for MurderessMoon.
 */
public class RoomReader   {

        GrassRender grassRender;
    ArrayList<TearsRender> tearsRenders = new ArrayList<TearsRender>();


    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private ArrayList<Grass> some = new ArrayList<Grass>();


    public RoomReader() {
         grassRender = new GrassRender();



        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, (w / h) *8.2f, 9.7f);
        //camera.update();

        // rm = dj;
       // map = new TmxMapLoader().load(rm.getTmx());
      //  renderer = new OrthogonalTiledMapRenderer(map, 1f / 32f);
    }

    public void render(float delta , Room rm) {

        if(map== null){
            map = new TmxMapLoader().load(rm.getTmx());
            renderer = new OrthogonalTiledMapRenderer(map, 1f / 32f);
        }
        renderer.setView(camera);
        renderer.render();



        try {
              some = ( ArrayList<Grass> ) Room.fromString(rm.elems);



        for (Grass elem : some) {
            grassRender.render(elem);
        }



        Iterator<TearsRender> iter = tearsRenders.iterator();
        while (iter.hasNext()) {
            TearsRender tearsRender = iter.next();
            for (Grass elem : some) {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public ArrayList<TearsRender> getTearsRenders() {
        return tearsRenders;
    }
}
