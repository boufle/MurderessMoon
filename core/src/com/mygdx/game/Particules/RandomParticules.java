package com.mygdx.game.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

/**
 * com.mygdx.game.Particules
 * Created by Theo on 07/11/2015 for MurderessMoon.
 */
public class RandomParticules implements Screen {


    ArrayList<ParticleEffect> particleEffects = new ArrayList<ParticleEffect>();
    Random r = new Random();

    private SpriteBatch batch;

    public RandomParticules() {

        batch = new SpriteBatch();

        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut12.p"), Gdx.files.internal("img"));
        particleEffects.add(effect);
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut1.p"), Gdx.files.internal("img"));
        particleEffects.add(effect);
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut2.p"), Gdx.files.internal("img"));
        particleEffects.add(effect);
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut3.p"), Gdx.files.internal("img"));
        particleEffects.add(effect);
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut4.p"), Gdx.files.internal("img"));
        particleEffects.add(effect); effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut5.p"), Gdx.files.internal("img"));
        particleEffects.add(effect); effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut6.p"), Gdx.files.internal("img"));
        particleEffects.add(effect); effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut7.p"), Gdx.files.internal("img"));
        particleEffects.add(effect); effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut8.p"), Gdx.files.internal("img"));
        particleEffects.add(effect); effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut9.p"), Gdx.files.internal("img"));
        particleEffects.add(effect); effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/salut10.p"), Gdx.files.internal("img"));
        particleEffects.add(effect);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        int R = r.nextInt(50 - 0) + 0;
        if (R == 1) {

            ParticleEffect pl = particleEffects.get(r.nextInt(particleEffects.size()));
            if (pl.isComplete()) {
                pl.setPosition(r.nextInt(Gdx.graphics.getWidth() ), r.nextInt(Gdx.graphics.getHeight() ));
                pl.start();
                pl.draw(batch, delta);
            }
        }
        for (ParticleEffect particleEffect : particleEffects) {
            if (!particleEffect.isComplete()) {
                particleEffect.draw(batch, delta);
            }
        }
        batch.end();
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
