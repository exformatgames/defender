package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;

public class NewPositionComponent implements Component {
	public float x = 0;
	public float y = 0;

	public NewPositionComponent init(float x, float y){
		this.x = x;
		this.y = y;

		return this;
	}
	
	private final static ComponentMapper<NewPositionComponent> mapper = ComponentMapper.getFor(NewPositionComponent.class);

	public static NewPositionComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
