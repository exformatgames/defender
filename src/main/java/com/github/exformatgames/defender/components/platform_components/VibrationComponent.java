package com.github.exformatgames.defender.components.platform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class VibrationComponent implements Component {

	public int millis = 0;

	private final static ComponentMapper<VibrationComponent> mapper = ComponentMapper.getFor(VibrationComponent.class);

	public static VibrationComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
