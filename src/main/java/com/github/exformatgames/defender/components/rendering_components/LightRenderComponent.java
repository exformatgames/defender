package com.github.exformatgames.defender.components.rendering_components;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class LightRenderComponent implements Component {

	public static RayHandler RAY_HANDLER = null;

	private final static ComponentMapper<LightRenderComponent> mapper = ComponentMapper.getFor(LightRenderComponent.class);

	public static LightRenderComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
