package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;

public class RotateComponent implements Component {
	
	public float degres = 0;

	private final static ComponentMapper<RotateComponent> mapper = ComponentMapper.getFor(RotateComponent.class);

	public static RotateComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
