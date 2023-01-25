package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.*;

public class GestureZoomComponent implements Component {

	public float initialDistance;
	public float endDistance;
	
	public float zoom;
	public float delta;

	private final static ComponentMapper<GestureZoomComponent> mapper = ComponentMapper.getFor(GestureZoomComponent.class);

	public static GestureZoomComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
