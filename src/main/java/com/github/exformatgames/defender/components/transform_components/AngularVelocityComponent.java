package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class AngularVelocityComponent implements Component {
	public float value = 0;

	private final static ComponentMapper<AngularVelocityComponent> mapper = ComponentMapper.getFor(AngularVelocityComponent.class);

	public static AngularVelocityComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
