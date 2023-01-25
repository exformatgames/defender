package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;

public class ScrollComponent implements Component {
	public float moveByX = 0;
	public float moveByY = 0;
	
	public boolean horizontal = false;
	public boolean vertical = false;

	public void init(float moveByX, float moveByY, boolean horizontal, boolean vertical) {
		this.moveByX = moveByX;
		this.moveByY = moveByY;
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	private final static ComponentMapper<ScrollComponent> mapper = ComponentMapper.getFor(ScrollComponent.class);

	public static ScrollComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
