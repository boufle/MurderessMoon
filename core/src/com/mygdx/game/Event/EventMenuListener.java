package com.mygdx.game.Event;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.Donjon.Generation;
import com.mygdx.game.screen.Game1;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MenuScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

/**
 * Created by Boufle on 03/11/2015.
 */
public class EventMenuListener implements InputProcessor {
    private MenuScreen menuScreen;

    public EventMenuListener(final MenuScreen menuScreen) {


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
           // menuScreen.getMurderessMoon().setScreen(new Game1(menuScreen.getMurderessMoon(), Generation.Generer()));
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
