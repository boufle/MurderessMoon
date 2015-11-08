package com.mygdx.game.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.Donjon.Donjon;
import com.mygdx.game.Donjon.Generation;
import com.mygdx.game.MurderessMoon;

/**
 * com.mygdx.game.screen
 * Created by Theo on 07/11/2015 for MurderessMoon.
 */

public class AssetLoader implements ApplicationListener {
    AssetManager manager = new AssetManager();
    private MurderessMoon murderessMoon;



    public AssetLoader( Donjon generer) {
        Generation.Generer();
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    public void render() {
        if (manager.update()) {
          //  murderessMoon.setScreen(new Game1(murderessMoon, Generation.Generer()));
            // we are done loading, let's move to another screen!
        }

        // display loading information
        float progress = manager.getProgress()  ;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}