package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class TransformLightComponent implements Component {
	public Vector2 position = new Vector2();
	public float dir = 0;

	public void init(Vector2 position, float dir) {
		this.position = position;
		this.dir = dir;
	}

	private final static ComponentMapper<TransformLightComponent> mapper = ComponentMapper.getFor(TransformLightComponent.class);

	public static TransformLightComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}

}
