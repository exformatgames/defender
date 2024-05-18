package com.github.exformatgames.defender.components.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

public class RayComponent implements Component {

	public Vector2 fromPoint = new Vector2();
	public Vector2 toPoint = new Vector2();
	public Vector2 collisionPoint = new Vector2();
	public Fixture collisionFixture;
	public float maxRayLength = 15;
	public float length = 0;
	public boolean isCast = false;

	public void init(Vector2 fromPoint, Vector2 toPoint){
		this.fromPoint.set(fromPoint);
		this.toPoint.set(toPoint);
		isCast = true;
	}

	public void init(float fromPointX, float fromPointY, float toPointX, float toPointY){
		fromPoint.set(fromPointX, fromPointY);
		toPoint.set(toPointX, toPointY);
		isCast = true;
	}

	private final static ComponentMapper<RayComponent> mapper = ComponentMapper.getFor(RayComponent.class);

	public static RayComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
