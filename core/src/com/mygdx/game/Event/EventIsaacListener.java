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

    public boolean isShootLeft = false;
    public boolean isShootgRight = false;
    public boolean isShootDown = false;
    public boolean isShootUp = false;

    public boolean isStay = true;

    public EventIsaacListener() {

    }

    @Override
    public boolean keyDown(int keycode) {


        System.out.println(keycode);
        if (keycode == 22 || keycode == 21 || keycode == 20 || keycode == 19) {
            isShootgRight = false;
            isShootLeft = false;
            isShootDown = false;
            isShootUp = false;

            if (keycode == 22) {

                isShootgRight = true;


            }
            if (keycode == 21) {
                isShootLeft = true;

            }
            if (keycode == 20) {
                isShootDown = true;

            }
            if (keycode == 19) {
                isShootUp = true;

            }

        }


        if (keycode == 32) {
            isRunningRight = true;

            isStay = false;


        }
        if (keycode == 45) {
            isRunningLeft = true;
            isStay = false;

        }
        if (keycode == 47) {
            isRunningDown = true;
            isStay = false;

        }
        if (keycode == 54) {
            isRunningUp = true;
            isStay = false;

        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 22) {
            isShootgRight = false;


        }
        if (keycode == 21) {
            isShootLeft = false;

        }
        if (keycode == 20) {
            isShootDown = false;

        }
        if (keycode == 19) {
            isShootUp = false;

        }


        if (keycode == 32) {
            isRunningRight = false;
            isStay = true;


        }
        if (keycode == 45) {
            isRunningLeft = false;
            isStay = true;

        }
        if (keycode == 47) {
            isRunningDown = false;
            isStay = true;

        }
        if (keycode == 54) {
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
