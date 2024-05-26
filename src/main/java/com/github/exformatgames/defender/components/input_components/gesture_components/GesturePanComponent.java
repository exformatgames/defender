package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class GesturePanComponent implements Component {

	public static Vector2 position = new Vector2(0, 0);
	public static Vector2 delta = new Vector2(0, 0);
	public static Vector2 direction = new Vector2(0, 1);
	public static Vector2 stop = new Vector2(0, 0);

	private final static ComponentMapper<GesturePanComponent> mapper = ComponentMapper.getFor(GesturePanComponent.class);

	public static GesturePanComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
