package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;

public class RotationComponent implements Component {
	
	public float degres = 0;
	
	private final static ComponentMapper<RotationComponent> mapper = ComponentMapper.getFor(RotationComponent.class);

	public static RotationComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
