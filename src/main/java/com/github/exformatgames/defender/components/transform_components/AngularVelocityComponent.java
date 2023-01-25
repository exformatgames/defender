package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;

public class AngularVelocityComponent implements Component {
	public float value = 0;
	
	private final static ComponentMapper<AngularVelocityComponent> mapper = ComponentMapper.getFor(AngularVelocityComponent.class);

	public static AngularVelocityComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
