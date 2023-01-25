package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;

public class TranslateComponent implements Component {
	public float x = 0;
	public float y = 0;

	public void init(float x, float y){
		this.x = x;
		this.y = y;
	}
	private final static ComponentMapper<TranslateComponent> mapper = ComponentMapper.getFor(TranslateComponent.class);

	public static TranslateComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}

}
