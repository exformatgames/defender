package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class ScaleComponent implements Component, Pool.Poolable {
	public float toX = 1;
	public float toY = 1;
	public float byX = 1;
	public float byY = 1;
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

    @Override
    public void reset() {
        toX = 1;
        toY = 1;
        byX = 1;
        byY = 1;
        isTo = true;
    }
}
