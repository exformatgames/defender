package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;

public class ScaleComponent implements Component {
	public float toX = 1;
	public float toY = 1;
	public float byX = 0;
	public float byY = 0;
	public boolean isTo = true;

	public void init(float toX, float toY, float byX, float byY, boolean isTo) {
		this.toX = toX;
		this.toY = toY;
		this.byX = byX;
		this.byY = byY;
		this.isTo = isTo;
	}

	private final static ComponentMapper<ScaleComponent> mapper = ComponentMapper.getFor(ScaleComponent.class);

	public static ScaleComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
