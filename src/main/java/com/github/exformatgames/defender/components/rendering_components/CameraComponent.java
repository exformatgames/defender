package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;

public class CameraComponent implements Component {
	public OrthographicCamera camera;
	
	private final static ComponentMapper<CameraComponent> mapper = ComponentMapper.getFor(CameraComponent.class);

	public static CameraComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
