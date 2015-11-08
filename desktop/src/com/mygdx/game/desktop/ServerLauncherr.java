package com.mygdx.game.desktop;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import com.mygdx.game.ServerLauncher;

import java.io.*;
import java.util.HashSet;

/**
 * com.mygdx.game.desktop
 * Created by Theo on 08/11/2015 for MurderessMoon.
 */
public class ServerLauncherr {

    public static void main (String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new ServerLauncher();
    }

}
