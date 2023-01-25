package com.github.exformatgames.defender.components.box2d;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class ForceComponent implements Component {
	public Vector2 point = new Vector2(0, 0);
	public Vector2 force = new Vector2(0, 0);
	
	public boolean center = false;

	public ForceComponent init(Vector2 point, Vector2 force, boolean center) {
		this.point.set(point);
		this.force.set(force);
		this.center = center;

		return this;
	}

	public ForceComponent init(Vector2 force) {
		this.point.set(0, 0);
		this.force.set(force);
		this.center = true;

		return this;
	}

	public ForceComponent init(float x, float y) {
		this.point.set(0, 0);
		this.force.set(x, y);
		this.center = true;

		return this;
	}

	private final static ComponentMapper<ForceComponent> mapper = ComponentMapper.getFor(ForceComponent.class);

	public static ForceComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
