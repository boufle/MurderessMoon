package com.mygdx.game;

import com.mygdx.game.Characters.Isaac;
import com.mygdx.game.room.Room;
import com.mygdx.game.room.RoomReader;

import javax.swing.*;
import java.util.HashMap;

/**
 * com.mygdx.game
 * Created by Theo on 08/11/2015 for MurderessMoon.
 */
public class UI {
  public   HashMap<Integer, Isaac> characters = new HashMap();
     public Room room = null;

    public String inputHost () {
        String input = (String) JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE,
                null, null, "localhost");
        if (input == null || input.trim().length() == 0) System.exit(1);
        return input.trim();
    }

    public String inputName () {
        String input = (String)JOptionPane.showInputDialog(null, "Name:", "Connect to server", JOptionPane.QUESTION_MESSAGE,
                null, null, "Test");
        if (input == null || input.trim().length() == 0) System.exit(1);
        return input.trim();
    }

    public String inputOtherStuff () {
        String input = (String)JOptionPane.showInputDialog(null, "Other Stuff:", "Create account", JOptionPane.QUESTION_MESSAGE,
                null, null, "other stuff");
        if (input == null || input.trim().length() == 0) System.exit(1);
        return input.trim();
    }

    public void addCharacter (Isaac character) {
        characters.put(character.getId(), character);
        System.out.println(character.getId() + " added at " + character.getX() + ", " + character.getY());
    }
    public void getRoom (Room room) {
        this.room = room;
    }

    public void updateCharacter (Network.UpdateCharacter msg) {
        Isaac character = characters.get(msg.id);
        if (character == null) return;
        character.setX(msg.x);
        character.setY(msg.y);
        character.setDirection(msg.direction);
        System.out.println(character.getId() + " moved to " + character.getX() + ", " + character.getY() + " d " +msg.direction);
    }

    public void removeCharacter (int id) {
        Isaac character = characters.remove(id);
        if (character != null) System.out.println(character.getId() + " removed");
    }

    public void updateroom(Network.RoomUpdate object) {
        System.out.println("UPDATE ROOM");
        room = object.room;
    }
}