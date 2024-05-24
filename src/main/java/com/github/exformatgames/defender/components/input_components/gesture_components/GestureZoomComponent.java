package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class GestureZoomComponent implements Component {

	public float initialDistance;
	public float endDistance;
	public float ratio;


	private final static ComponentMapper<GestureZoomComponent> mapper = ComponentMapper.getFor(GestureZoomComponent.class);

	public static GestureZoomComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
