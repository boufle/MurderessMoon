package com.mygdx.game;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.mygdx.game.Characters.Isaac;
import com.mygdx.game.Elements.Elements;
import com.mygdx.game.Elements.Grass;
import com.mygdx.game.Floor.TypeRoom;
import com.mygdx.game.room.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * com.mygdx.game
 * Created by Theo on 08/11/2015 for MurderessMoon.
 */
public class Network {
    static public final int port = 3389;

    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Login.class);
        kryo.register(RegistrationRequired.class);
        kryo.register(Register.class);
        kryo.register(AddCharacter.class);
        kryo.register(UpdateCharacter.class);
        kryo.register(RemoveCharacter.class);
        kryo.register(Isaac.class);
        kryo.register(MoveCharacter.class);
        kryo.register(RoomUpdate.class);
        kryo.register(TypeRoom.class);
        kryo.register(Elements.class);
        kryo.register(Grass.class);

        kryo.register(ArrayList.class);
        kryo.register(LinkedList.class);
        kryo.register(CopyOnWriteArrayList.class);
        kryo.register(HashMap.class);

        kryo.register(Room.class);
    }

    static public class Login {
        public int name;
    }

    static public class RegistrationRequired {
    }

    static public class Register {
        public int name;
    }

    static public class UpdateCharacter {
        public int id, x, y,direction;
    }


    static public class AddCharacter {
        public Isaac character;
    }

    static public class RemoveCharacter {
        public int id;
    }

    static public class MoveCharacter {
        public int x, y;
    }
    static public class RoomUpdate {
        public Room room;
    }


}