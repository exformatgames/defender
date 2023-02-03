package com.github.exformatgames.defender.components.box2d;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class BodyComponent implements Component {

	public Body body = null;

	private Entity entityUserData = null;

	public Vector2 oldPosition = new Vector2();
	public float oldRotation = 0;

	public BodyComponent init(Body body){
		this.body = body;
		oldPosition.set(body.getPosition());
		oldRotation = body.getAngle();

		if (entityUserData != null) {
			body.setUserData(entityUserData);
		}

		return this;
	}

	public BodyComponent setDamping(float linear, float angular){
		body.setLinearDamping(linear);
		body.setAngularDamping(angular);
		return this;
	}

	public BodyComponent setFriction(float value){
		body.getFixtureList().first().setFriction(value);

		return this;
	}

	public BodyComponent setFriction(int indexFixture, float value){
		body.getFixtureList().get(indexFixture).setFriction(value);

		return this;
	}

	public BodyComponent setFixedRotation(boolean flag){

		body.setFixedRotation(flag);

		return this;
	}

	public void setEntityInUserData(Entity entity) {
		this.entityUserData = entity;
	}

	public BodyComponent setFilter(Filter filter){
		for (int i = 0; i < body.getFixtureList().size; i++) {
			Fixture fixture = body.getFixtureList().get(i);
			fixture.setFilterData(filter);
		}

		return this;
	}

	public BodyComponent setFilter(Filter filter, int fixtureIndex){
		body.getFixtureList().get(fixtureIndex).setFilterData(filter);

		return this;
	}

	public BodyComponent setFilter(short mask, short category, short group, int fixtureIndex){
		Filter filter = new Filter();
		filter.maskBits = mask;
		filter.categoryBits = category;
		filter.groupIndex = group;

		setFilter(filter, fixtureIndex);

		return this;
	}

	public BodyComponent setFilter(short mask, short category, short group){
		Filter filter = new Filter();
		filter.maskBits = mask;
		filter.categoryBits = category;
		filter.groupIndex = group;

		setFilter(filter);

		return this;
	}

	public BodyComponent setFilter(short mask, short category){

		setFilter(mask, category, (short) 0);

		return this;
	}

	public BodyComponent setBullet(boolean flag) {
		body.setBullet(flag);

		return this;
	}

	public BodyComponent setRestitution(float value) {
		body.getFixtureList().first().setRestitution(value);

		return this;
	}

	public BodyComponent setSensor(boolean flag) {
		body.getFixtureList().first().setSensor(flag);

		return this;
	}

	public BodyComponent setSensor(int fixtureId, boolean flag) {
		body.getFixtureList().get(fixtureId).setSensor(flag);

		return this;
	}

	public BodyComponent setRestitution(int fixtureId, float value) {
		body.getFixtureList().get(fixtureId).setRestitution(value);

		return this;
	}

	private final static ComponentMapper<BodyComponent> mapper = ComponentMapper.getFor(BodyComponent.class);

	public static BodyComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
