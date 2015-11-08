package com.mygdx.game.Characters;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TearsRender;
import com.mygdx.game.screen.Game1;

import java.io.Serializable;

/**
 * Created by Boufle on 02/11/2015.
 */
public class Isaac implements Serializable {

     protected int x , y,speed=4,direction = 0;
    protected double damage = 1;

    private int id = 0;
    protected double attSpeed = 30;
    protected int heathResist =0;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getRange() {
        return range;
    }

    public int getId() {
        return id;
    }

    public int getHeath() {
        return heath;
    }

    public double getAttSpeedTemp() {
        return attSpeedTemp;
    }

    public int getHeathResist() {
        return heathResist;
    }

    public int getSpeed() {
        return speed;
    }

    public double getDamage() {
        return damage;
    }

    protected int range =60;


    protected double attSpeedTemp =0;
    protected int heath = 10;

    public int currentheath = heath;


    public Isaac(int name){
        this.id =  name;
        //this.game1 = game1;
        x = 588;
        y = 364;

    }
    public Isaac(){
        this.id =  (int) (Math.random() * 50 + 1);
        //this.game1 = game1;
        x = 588;
        y = 364;

    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x>= 124 && x<= 1096){
            this.x = x;
        }

    }

    public int getY() {
        return y;
    }

    public double getAttSpeed() {
        return attSpeed;
    }
    public void setY(int y) {
        if(y<= 580 && y>= 120){

            this.y = y;
        }
    }


    public void shoot(Vector2 direction, Game1 game1) {

         game1.getRoomReader().getTearsRenders().add( new TearsRender(this,direction));
         this.attSpeedTemp = this.attSpeed;
    }

    public void hurt(Integer damage) {
        if(heathResist<= 0){

            currentheath-=damage;
            heathResist = 60 ;
        }

       // game1.getTearsRenders().add( new TearsRender(this,direction));
       // this.attSpeedTemp = this.attSpeed;
    }


    public void setId(int id) {
        this.id = id;
    }
}
