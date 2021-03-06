package com.mygdx.game.Donjon;


import com.mygdx.game.Floor.Floor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * isaac.Donjon
 * Created by Theo on 02/11/2015 for helloworld.
 */
public class Donjon implements Serializable {
    ArrayList<Floor> floors = new ArrayList<Floor>();
    Integer current = 1;
    Integer seed ;

    public Donjon(Integer seed){
        this.seed = seed;
        for (int i = 0; i < 2; i++) {
            floors.add(new Floor(i,seed));
        }
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public Integer getCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return "Donjon{" +
                "floors=" + floors +
                ", seed=" + seed +
                '}';
    }

}