package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class GestureTapComponent implements Component {

	public Vector2 position = new Vector2();
	public int count = 0;

	private final static ComponentMapper<GestureTapComponent> mapper = ComponentMapper.getFor(GestureTapComponent.class);

	public static GestureTapComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
