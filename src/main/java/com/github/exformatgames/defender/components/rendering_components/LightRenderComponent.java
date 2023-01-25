package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.*;
import box2dLight.*;

public class LightRenderComponent implements Component {
	
	public static RayHandler RAY_HANDLER = null;
	
	private final static ComponentMapper<LightRenderComponent> mapper = ComponentMapper.getFor(LightRenderComponent.class);

	public static LightRenderComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
