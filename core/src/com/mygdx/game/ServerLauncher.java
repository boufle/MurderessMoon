package com.mygdx.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.game.Characters.Isaac;
import com.mygdx.game.Floor.TypeRoom;
import com.mygdx.game.room.Room;

import java.io.*;
import java.util.HashSet;

/**
 * com.mygdx.game
 * Created by Theo on 08/11/2015 for MurderessMoon.
 */
public class ServerLauncher {
    Server server;
    HashSet<Isaac> loggedIn = new HashSet();
    private Room activeroom = new Room(TypeRoom.Starter,1,0,0);

    public ServerLauncher () throws IOException {
        server = new Server() {
            protected Connection newConnection () {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new CharacterConnection();
            }
        };

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(server);

        server.addListener(new Listener() {
            public void received (Connection c, Object object) {
                // We know all connections for this server are actually CharacterConnections.
                CharacterConnection connection = (CharacterConnection)c;
                Isaac character = connection.character;
                System.out.println("c = [" + c + "], object = [" + object + "]");
                if (object instanceof Network.RoomUpdate) {

                Network.RoomUpdate roomUpdate = new Network.RoomUpdate();
                roomUpdate.room = activeroom;
                c.sendUDP(roomUpdate);

                }
                if (object instanceof Network.Login) {
                    // Ignore if already logged in.
                    if (character != null) return;

                    // Reject if the name is invalid.
                    int name = ((Network.Login)object).name;
                    if (!isValid(name)) {
                        c.close();
                        return;
                    }

                    // Reject if already logged in.
                    for (Isaac other : loggedIn) {
                        if ( String.valueOf(other.getId()) .equals(name )) {
                            c.close();
                            return;
                        }
                    }

                    character = loadCharacter(name);

                    // Reject if couldn't load character.
                    if (character == null) {
                        character = new Isaac(name);
                        // character.name = register.name;
                        //character.otherStuff = register.otherStuff;
                        //character.x = 0;
                        // character.y = 0;
                        if (!saveCharacter(character)) {
                           // c.close();

                        }

                        /* c.sendTCP(new Network.RegistrationRequired());
                        return;*/
                    }

                    loggedIn(connection, character);


                    Network.RoomUpdate roomUpdate = new Network.RoomUpdate();
                    roomUpdate.room = activeroom;
                    c.sendUDP(roomUpdate);

                    return;
                }

                if (object instanceof Network.Register) {
                    // Ignore if already logged in.
                    if (character != null) return;

                    Network.Register register = (Network.Register)object;

                    // Reject if the login is invalid.
                    if (!isValid(register.name)) {
                        c.close();
                        return;
                    }


                    // Reject if character alread exists.
                    if (loadCharacter(register.name) != null) {
                        c.close();
                        return;
                    }

                    character = new Isaac(register.name);
                   // character.name = register.name;
                    //character.otherStuff = register.otherStuff;
                    //character.x = 0;
                   // character.y = 0;
                    if (!saveCharacter(character)) {
                        c.close();
                        return;
                    }

                    loggedIn(connection, character);
                    return;
                }

                if (object instanceof Network.UpdateCharacter) {
                    // Ignore if not logged in.
                    if (character == null) return;

                    Network.UpdateCharacter msg = (Network.UpdateCharacter)object;

                    // Ignore if invalid move.
                    //if (Math.abs(msg.x) != 1 && Math.abs(msg.y) != 1) return;

                    character.setX(msg.x);
                    character.setY(msg.y);
                    character.setDirection(msg.direction);
                    if (!saveCharacter(character)) {
                        connection.close();
                        return;
                    }

                    Network.UpdateCharacter update = new Network.UpdateCharacter();
                    update.id = character.getId();
                    update.x = character.getX();
                    update.y = character.getY();
                    update.direction = character.getDirection();
                    server.sendToAllUDP(update);
                    return;
                }

            }

            private boolean isValid (int value) {
                if (value == 0) return false;
               // value = value.trim();
              //  if (value.length() == 0) return false;
                return true;
            }

            public void disconnected (Connection c) {
                CharacterConnection connection = (CharacterConnection)c;
                if (connection.character != null) {
                     loggedIn.remove(connection.character);
                    Network.RemoveCharacter removeCharacter = new Network.RemoveCharacter();
                    removeCharacter.id = connection.character.getId();
                    server.sendToAllUDP(removeCharacter);
                }
            }
        });
        server.bind(Network.port,Network.port);
        server.start();
    }

    void loggedIn (CharacterConnection c, Isaac character) {
        c.character = character;

        // Add existing characters to new logged in connection.
        for (Isaac other : loggedIn) {
            Network.AddCharacter addCharacter = new Network.AddCharacter();
            addCharacter.character = other;
            c.sendUDP(addCharacter);
        }




        loggedIn.add(character);

        // Add logged in character to all connections.
        Network.AddCharacter addCharacter = new Network.AddCharacter();
        addCharacter.character = character;
        server.sendToAllUDP(addCharacter);
    }

    boolean saveCharacter (Isaac character) {
        File file = new File("characters", String.valueOf(character.getId()));
        file.getParentFile().mkdirs();

        if (character.getId() == 0) {
            String[] children = file.getParentFile().list();
            if (children == null) return false;
            character.setId(children.length + 1);
        }

        DataOutputStream output = null;
        try {
            output = new DataOutputStream(new FileOutputStream(file));
            output.writeInt(character.getId());
           // output.writeUTF(character.otherStuff);
            output.writeInt(character.getX());
            output.writeInt(character.getY());
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                output.close();
            } catch (IOException ignored) {
            }
        }
    }

    Isaac loadCharacter (int name) {
        File file = new File("characters", String.valueOf(name));
        if (!file.exists()) return null;
        DataInputStream input = null;
        try {
            input = new DataInputStream(new FileInputStream(file));
            Isaac character = new Isaac(name);
            character.setId(input.readInt()) ;
          //  character.name = name;
           // character.otherStuff = input.readUTF();
            character.setX(input.readInt());
            character.setY(input.readInt());
            input.close();
            return character;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (input != null) input.close();
            } catch (IOException ignored) {
            }
        }
    }

    // This holds per connection state.
    static class CharacterConnection extends Connection {
        public Isaac character;
    }

}
