package com.github.exformatgames.defender.components.audio_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.*;

public class MusicComponent implements Component {

	public Music music = null;

	public boolean remove = false;
	public boolean pause = false;
	public boolean stop = false;
	public boolean play = false;

	public float volume = 0;

	private static ComponentMapper<MusicComponent> mapper = ComponentMapper.getFor(MusicComponent.class);

	public static MusicComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
