package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class GesturePanComponent implements Component {

	public Vector2 position = new Vector2(0, 0);
	public Vector2 delta = new Vector2(0, 0);
	public Vector2 direction = new Vector2(0, 1);
	public Vector2 stop = new Vector2(0, 0);
	
	private final static ComponentMapper<GesturePanComponent> mapper = ComponentMapper.getFor(GesturePanComponent.class);

	public static GesturePanComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
