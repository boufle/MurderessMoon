package com.mygdx.game.Event;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by Boufle on 03/11/2015.
 */
public class EventIsaacListener implements InputProcessor {

    public boolean isRunningLeft = false;
    public boolean isRunningRight = false;
    public boolean isRunningDown = false;
    public boolean isRunningUp = false;
    public boolean isStay = true;

    public EventIsaacListener( ) {

    }

    @Override
    public boolean keyDown(int keycode) {


        System.out.println(keycode);
        if (keycode == 22){
            isRunningRight = true;
            isStay = false;


        }
        if (keycode == 21){
            isRunningLeft = true;
            isStay = false;

        }
        if (keycode == 20){
            isRunningDown = true;
            isStay = false;

        }
        if (keycode == 19){
            isRunningUp = true;
            isStay = false;

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 22){
            isRunningRight = false;
            isStay = true;


        }
        if (keycode == 21){
            isRunningLeft = false;
            isStay = true;

        }
        if (keycode == 20){
            isRunningDown = false;
            isStay = true;

        }
        if (keycode == 19){
            isRunningUp = false;
            isStay = true;

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
