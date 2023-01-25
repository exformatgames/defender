package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.*;

public class ZIndexComponent implements Component{
	
	public int zIndex = 0;

	private static final ComponentMapper<ZIndexComponent> mapper = ComponentMapper.getFor(ZIndexComponent.class);

	public static ZIndexComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
