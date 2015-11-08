package com.mygdx.game.Floor;

import com.mygdx.game.room.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * isaac.Floor
 * Created by Theo on 02/11/2015 for helloworld.
 */
public class Floor implements Serializable {

    ArrayList<Room> rm = new ArrayList<Room>();
    private Integer lvl;

    public Floor(Integer lvl, Integer seed){
        Random rand = new Random(seed);
        int nombreAleatoire = rand.nextInt(2 - 1 + 1) + 1;

        //starter Room
       Room start =  new Room(TypeRoom.Starter,0,0,0);
        rm.add(start);

        this.lvl = lvl;
        while (nombreAleatoire>0){
            boolean room = rand.nextBoolean();
            int el = rand.nextInt(4 - 1 + 1) + 1;
            switch (el){
                case 1:
                    Room rom =new Room(TypeRoom.Normal,0,0,1);
                    rm.add(rom);
                    nombreAleatoire--;
                    break;
                case 2:
                    Room rom1 =new Room(TypeRoom.Normal,0,0,-1);
                    rm.add(rom1);
                    nombreAleatoire--;
                    break;
                case 3:
                    Room rom3 =new Room(TypeRoom.Normal,0,-1,0);
                    rm.add(rom3);
                    nombreAleatoire--;
                    break;
                case 4:
                    Room rom2 =new Room(TypeRoom.Normal,0,1,0);
                    rm.add(rom2);
                    nombreAleatoire--;
                    break;
            }

        }


    }

    public  Room  getRm(int x , int y) {
        for (Room room : rm) {
            if(room.getX()== x && room.getY() == y){
                return room;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "rm=" + rm +
                ", lvl=" + lvl +
                '}';
    }
}
