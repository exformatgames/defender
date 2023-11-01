package com.github.exformatgames.defender.assets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;

public class BitmapFonts {
	
	private Array<BitmapFont> fonts = new Array<>();
	private Array<String> fontNames = new Array<>();


	public void add(BitmapFont sound, String name) {
		fonts.add(sound);
		fontNames.add(name);
	}

	public BitmapFont get(String name) {
		for (int i = 0; i < fontNames.size; i++) {
			if (name.equals(fontNames.get(i))) {
				return fonts.get(i);
			}
		}
		return null;
	}
	
}
