package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class GestureRotateComponent implements Component {

	public Vector2 rotationPoint = new Vector2(0, 0);
	public float degres = 0;
	public float delta = 0;

	private final static ComponentMapper<GestureRotateComponent> mapper = ComponentMapper.getFor(GestureRotateComponent.class);

	public static GestureRotateComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
