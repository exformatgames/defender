package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class SizeComponent implements Component {
	public float width = 0;
	public float height = 0;
	
	public float halfWidth = 0;
	public float halfHeight = 0;
	
	public Vector2 size = new Vector2();

	public void init(Vector2 size) {
		this.size.set(size);
		width = size.x;
		height = size.y;
		halfWidth = size.x / 2;
		halfHeight = size.y / 2;
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

}
