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

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;

/**
 * isaac.room
 * Created by Theo on 02/11/2015 for helloworld.
 */
public class Room implements Serializable {

    private   TypeRoom type = TypeRoom.Starter;
    private   Integer id = 1;
    private   Integer x=0;
    private   Integer y=0;
    public    String elems;



    private String tmx = "Room/Room1.tmx";


    public Room(TypeRoom type ,Integer id, Integer X , Integer Y) {
        this.type = type;
        this.id = id;
        x = X;
        y = Y;
        ArrayList<Grass> elems = new ArrayList<Grass>();
         elems.add(new Grass(450,800));
        elems.add(new Grass(450+64,800));
         elems.add(new Grass(450-64-64,800));
         elems.add(new Grass(200,800));

        try {
            this.elems = toString(elems);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public Room( ) {

    }

    @Override
    public String toString() {
        return "Room{" +
                "type=" + type +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +

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
    /** Read the object from Base64 string. */
    public static Object fromString( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    public static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
