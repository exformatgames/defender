package com.github.exformatgames.defender.assets;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

public class Sounds {

	private Array<Sound> sounds = new Array<>();
	private Array<String> soundsNames = new Array<>();


	public void add(Sound sound, String name) {
		sounds.add(sound);
		soundsNames.add(name);
	}

	public Sound get(String name) {
		for (int i = 0; i < soundsNames.size; i++) {
			if (name.equals(soundsNames.get(i))) {
				return sounds.get(i);
			}
		}
		return null;
	}

    public void dispose() {
        for (Sound sound: sounds) {
            sound.dispose();
        }
    }
}
