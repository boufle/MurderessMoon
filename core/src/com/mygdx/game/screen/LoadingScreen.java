package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.mygdx.game.Donjon.Generation;
import com.mygdx.game.MurderessMoon;

/**
 * Created by Boufle on 02/11/2015.
 */
public class LoadingScreen implements Screen {



    public LoadingScreen(MurderessMoon murderessMoon) {
        murderessMoon.setScreen(new Game1(murderessMoon, Generation.Generer()));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
