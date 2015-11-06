package com.mygdx.game.Event;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Donjon.Generation;
import com.mygdx.game.screen.Game1;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MenuScreen;

/**
 * Created by Boufle on 03/11/2015.
 */
public class EventMenuListener implements InputProcessor {
    private MenuScreen menuScreen;

    public EventMenuListener(MenuScreen menuScreen) {

        this.menuScreen = menuScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
      //  System.out.println("character = [" + character + "]");
        if (character == ' '){
            menuScreen.getMurderessMoon().setScreen(new Game1(menuScreen.getMurderessMoon(), Generation.Generer()));
          //  menuScreen.getMurderessMoon().setScreen(new LoadingScreen(menuScreen.getMurderessMoon()));
        }
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
