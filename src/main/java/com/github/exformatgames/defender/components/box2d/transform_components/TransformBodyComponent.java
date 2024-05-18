package com.github.exformatgames.defender.components.box2d.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class TransformBodyComponent implements Component {
	public Vector2 position = new Vector2();
	public float angularVelocity = 0;
	public Vector2 linearVelocity = new Vector2();
	public float angle = 0;
	public boolean awake = false;

	public TransformBodyComponent init(Vector2 position, float angularVelocity, Vector2 linearVelocity, float angle, boolean awake) {
		this.position.set(position);
		this.angularVelocity = angularVelocity;
		this.linearVelocity.set(linearVelocity);
		this.angle = angle;
		this.awake = awake;

		return this;
	}

    public TransformBodyComponent init(float x, float y, float angle, boolean awake) {
        this.position.set(position);
        this.angle = angle;
        this.awake = awake;

        return this;
    }

	private final static ComponentMapper<TransformBodyComponent> mapper = ComponentMapper.getFor(TransformBodyComponent.class);

	public static TransformBodyComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
