package com.github.exformatgames.defender.components.math_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Circle;

public class CircleComponent implements Component {

	public Circle circle = new Circle();

	private final static ComponentMapper<CircleComponent> mapper = ComponentMapper.getFor(CircleComponent.class);

	public static CircleComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
