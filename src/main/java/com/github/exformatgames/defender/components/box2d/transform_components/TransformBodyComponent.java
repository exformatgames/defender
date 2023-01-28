package com.github.exformatgames.defender.components.box2d.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class TransformBodyComponent implements Component {
	public Vector2 position = new Vector2();
	public float angularVelocity = 0;
	public float angle = 0;
	public boolean awake = false;
	
	
	
	private final static ComponentMapper<TransformBodyComponent> mapper = ComponentMapper.getFor(TransformBodyComponent.class);

	public static TransformBodyComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
