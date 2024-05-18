package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class SizeComponent implements Component, Pool.Poolable {
	public float width = 0;
	public float height = 0;

	public float halfWidth = 0;
	public float halfHeight = 0;

	public Vector2 size = new Vector2();

	public void init(Vector2 size) {
		init(size.x, size.y);
	}

    public void init(float size) {
        init(size, size);
    }

	public void init(float width, float height){
		size.set(width, height);
		this.width = size.x;
		this.height = size.y;
		halfWidth = size.x / 2;
		halfHeight = size.y / 2;
	}

	private final static ComponentMapper<SizeComponent> mapper = ComponentMapper.getFor(SizeComponent.class);

	public static SizeComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}

    @Override
    public void reset() {
        width = 0;
        height = 0;
        halfWidth = 0;
        halfHeight = 0;
        size.setZero();
    }
}
