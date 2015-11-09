package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.Characters.Isaac;
import com.mygdx.game.Characters.IsaacRender;
import com.mygdx.game.Donjon.Donjon;
import com.mygdx.game.MurderessMoon;
import com.mygdx.game.Network;
import com.mygdx.game.UI;
import com.mygdx.game.room.RoomReader;

import java.io.IOException;

/**
 * Created by Boufle on 02/11/2015.
 */
public class Game1 implements Screen {

    private   IsaacRender isaacrenderer;
    private RoomReader roomReader;
    SpriteBatch batch;
    UI ui;
    int name;
    Client client;



    private BitmapFont font;
    float stateTime;


    public Game1(MurderessMoon murderessMoon, Donjon dj) {
        client = new Client();
        client.start();

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(client);

        // ThreadedListener runs the listener methods on a different thread.
        client.addListener(new Listener.ThreadedListener(new Listener() {
            public void connected(Connection connection) {
                System.out.println("connection = [" + connection + "]");
            }

            public void received(Connection connection, Object object) {
                System.out.println("connection = [" + connection + "], object = [" + object + "]");
                if (object instanceof Network.RegistrationRequired) {
                    Network.Register register = new Network.Register();
                    //register.name = get;
                    //register.otherStuff = ui.inputOtherStuff();
                    client.sendUDP(register);
                }

                if (object instanceof Network.AddCharacter) {
                    Network.AddCharacter msg = (Network.AddCharacter) object;
                    ui.addCharacter(msg.character);
                    return;
                }

                if (object instanceof Network.UpdateCharacter) {
                    ui.updateCharacter((Network.UpdateCharacter) object);
                    return;
                }

                if (object instanceof Network.RoomUpdate) {
                    ui.updateroom((Network.RoomUpdate) object);
                    return;
                }

                if (object instanceof Network.RemoveCharacter) {
                    Network.RemoveCharacter msg = (Network.RemoveCharacter) object;
                    ui.removeCharacter(msg.id);
                    return;
                }
            }

            public void disconnected(Connection connection) {
                System.exit(0);
            }
        }));

        ui = new UI();

        //String host = ui.inputHost();
        String host = "localhost";
         try {
            client.connect(5000, host, Network.port ,Network.port  );
            // Server communication after connection can go here, or in Listener#connected().
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        name = (int) (Math.random() * 50 + 1);
        //name = ui.inputName();
        Network.Login login = new Network.Login();
        login.name = name;
        client.sendUDP(login);

       // Network.RoomUpdate upd = new Network.RoomUpdate();
         //client.sendUDP(upd);


        batch = new SpriteBatch();


        stateTime = 0f;

        font = new BitmapFont();

        isaacrenderer = new IsaacRender();


        //  Network.AddCharacter add = new Network.AddCharacter();
        //add.character = isaac;
        //client.sendUDP(add);

        // ui.addCharacter(isaac);

        roomReader = new RoomReader();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0.2f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        if (client.isConnected()) {


            // asset.render();
            if(ui.room !=null){
                roomReader.render(delta,ui.room);
            }


            // System.out.println("delta = [" + ui.characters.size() + "]");


            for (Isaac character : ui.characters.values()) {
                //IsaacRender render = new IsaacRender(character);
                //render.render(delta);

                if(character.getId()==name){
                    isaacrenderer.render(delta,character,true,client,this);
                }else {
                    isaacrenderer.render(delta,character,false, client, this);
                }
            }


            batch.begin();
            font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
            batch.end();
        }else {
            batch.begin();
            font.draw(batch, "Connexion impossible " , 600, 400);
            font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
            batch.end();
        }


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

    public RoomReader getRoomReader() {
        return roomReader;
    }
}
