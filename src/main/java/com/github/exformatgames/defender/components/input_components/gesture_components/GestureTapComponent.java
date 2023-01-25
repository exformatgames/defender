package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class GestureTapComponent implements Component {

	public Vector2 position = new Vector2();
	public int count = 0;
	
	private final static ComponentMapper<GestureTapComponent> mapper = ComponentMapper.getFor(GestureTapComponent.class);

	public static GestureTapComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
