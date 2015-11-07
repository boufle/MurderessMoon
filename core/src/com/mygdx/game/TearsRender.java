package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Characters.Isaac;

import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;

/**
 * com.mygdx.game
 * Created by Theo on 07/11/2015 for MurderessMoon.
 */
public class TearsRender implements Screen {
    private Vector2 direct;
    private  ParticleEffect effect;
    private   SpriteBatch batch;
    Texture img;
    double rangetemp;
    TextureRegion[] test;
    private Isaac is;
    private Vector2 vector2;
    public boolean isdead = false;

    public TearsRender(Isaac is, Vector2 direct) {
        this.is = is;
        this.direct = direct;
        batch = new SpriteBatch();
        vector2= new Vector2(is.getX(),is.getY());
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/tears.p"), Gdx.files.internal("img"));

        effect.start();

        rangetemp = is.getRange();

        img = new Texture("spirits\\tears.png");
        tears();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        //vector2.scl(direct);
        int speed = 6 ;
        vector2.add(direct.x *speed, direct.y *speed );
        if(rangetemp <=0){
            effect.setPosition(vector2.x,vector2.y);
            effect.draw(batch, delta);
            if(effect.isComplete()){
                isdead = true;
            }
        }else {
            batch.draw(test[6],vector2.x,vector2.y, 64 ,64);
            rangetemp--;
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


    public  void tears(){

        TextureRegion[][] tmp = TextureRegion.split(img, img.getWidth() / 8, img.getHeight() / 4);
        test = new TextureRegion[4 * 8];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                test[index++] = tmp[i][j];
            }
        }


    }
}
