package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MurderessMoon;
import org.lwjgl.opengl.Display;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Murderess Moon";
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		double width = gd.getDisplayMode().getWidth()/1.5;
		double height = gd.getDisplayMode().getHeight()/1.5;
		config.height = (int) height;
		config.width = (int) width;
		new LwjglApplication(new MurderessMoon(), config);
	}
}
