package com.mygdx.game.Characters;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TearsRender;
import com.mygdx.game.screen.Game1;

/**
 * Created by Boufle on 02/11/2015.
 */
public class Isaac {

    private   IsaacRender render;
    protected int x , y,speed=4;
    protected double damage = 1;


    protected double attSpeed = 30;
    protected int heathResist =0;

    public int getRange() {
        return range;
    }

    protected int range =60;


    protected double attSpeedTemp =0;
    protected int heath = 10;

    public int currentheath = heath;
    private Game1 game1;

    public Isaac(Game1 game1){
        this.game1 = game1;
        x = 588;
        y = 364;
       render = new IsaacRender(this);
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

    public void getRender(float delta) {
        render.render(delta);
    }

    public void shoot(Vector2 direction) {

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



}
