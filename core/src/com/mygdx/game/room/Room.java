package com.mygdx.game.room;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Elements.Elements;
import com.mygdx.game.Elements.Grass;
import com.mygdx.game.Floor.TypeRoom;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * isaac.room
 * Created by Theo on 02/11/2015 for helloworld.
 */
public class Room implements Serializable {

    private final TypeRoom type;
    private final Integer id;
    private final Integer x;
    private final Integer y;
    ArrayList<Elements> elems = new ArrayList<Elements>();


    private String tmx = "Room/Room1.tmx";


    public Room(TypeRoom type ,Integer id, Integer X , Integer Y) {
        this.type = type;
        this.id = id;
        x = X;
        y = Y;


        elems.add(new Grass(450,800));
        elems.add(new Grass(450+64,800));
        elems.add(new Grass(450-64-64,800));
        elems.add(new Grass(200,800));






    }

    @Override
    public String toString() {
        return "Room{" +
                "type=" + type +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", elems=" + elems +
                '}';
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getTmx() {
        return tmx;
    }
}
