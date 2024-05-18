package com.github.exformatgames.defender.assets;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

public class Musics {

	private Array<Music> musics = new Array<>();
	private Array<String> musicNames = new Array<>();


	public void add(Music music, String name) {
		musics.add(music);
		musicNames.add(name);
	}

	public Music get(String name) {
		for (int i = 0; i < musicNames.size; i++) {
			if (name.equals(musicNames.get(i))) {
				return musics.get(i);
			}
		}
		return null;
	}

    public void dispose() {
        for (Music music: musics) {
            music.dispose();
        }
    }
}
