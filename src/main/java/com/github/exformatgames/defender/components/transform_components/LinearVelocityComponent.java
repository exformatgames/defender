package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pools;

public class LinearVelocityComponent implements Component {

	public Vector2 value = Pools.obtain(Vector2.class);

	public void init(float x, float y){
		value.set(x, y);
	}

	private final static ComponentMapper<LinearVelocityComponent> mapper = ComponentMapper.getFor(LinearVelocityComponent.class);

	public static LinearVelocityComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
