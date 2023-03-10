package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.*;

public class CullingComponent implements Component {
	public boolean inViewport = true;
	public float visibleRadius = 0;
	
	private final static ComponentMapper<CullingComponent> mapper = ComponentMapper.getFor(CullingComponent.class);

	public static CullingComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
