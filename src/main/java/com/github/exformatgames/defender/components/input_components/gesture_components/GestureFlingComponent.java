package com.github.exformatgames.defender.components.input_components.gesture_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class GestureFlingComponent implements Component {
	
	public Vector2 velocity = new Vector2(0, 0);

	public SideFling sideFling = SideFling.NULL;

	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	
	private final static ComponentMapper<GestureFlingComponent> mapper = ComponentMapper.getFor(GestureFlingComponent.class);

	public static GestureFlingComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}

	public enum SideFling{
		UP,
		DOWN,
		LEFT,
		RIGHT,
		NULL
	}
}
