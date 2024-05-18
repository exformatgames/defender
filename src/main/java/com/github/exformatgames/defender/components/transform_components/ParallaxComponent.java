package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class ParallaxComponent implements Component {
	public int layer = 0;

	public boolean horizontal = false;
	public boolean vertical = false;

	public void init(int layer, boolean horizontal, boolean vertical) {
		this.layer = layer;
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	private final static ComponentMapper<ParallaxComponent> mapper = ComponentMapper.getFor(ParallaxComponent.class);

	public static ParallaxComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
