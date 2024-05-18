package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class GestureLongPressComponent implements Component {

	public Vector2 position = new Vector2();

	private final static ComponentMapper<GestureLongPressComponent> mapper = ComponentMapper.getFor(GestureLongPressComponent.class);

	public static GestureLongPressComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
