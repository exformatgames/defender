package com.github.exformatgames.defender.utils;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class BodyBuilder {

	private static World world;
	private static BodyDef bodyDef = new BodyDef();
	private static FixtureDef fixtureDef = new FixtureDef();
	private static CircleShape circleShape = new CircleShape();
	private static PolygonShape polygonShape = new PolygonShape();

	public static void init(World world) {
		BodyBuilder.world = world;
	}

	public static Body buildModel(BodyEditorLoader loader, String name, BodyDef.BodyType type, Vector2 position, float scl){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		bodyDef.angle = 0;
		Body body = world.createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 0.55f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.3f;

		loader.attachFixture(body, name, fixtureDef, scl);
		return body;
	}
	public static Body buildModel(BodyEditorLoader loader, String name, BodyDef.BodyType type, FixtureDef fixtureDef, Vector2 position, float scl){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		bodyDef.angle = 0;
		Body body = world.createBody(bodyDef);

		loader.attachFixture(body, name, fixtureDef, scl);
		return body;
	}
	public static Body buildBox(BodyDef.BodyType type, Vector2 position, float width, float height) {
		return buildBox(type, position.x, position.y, width, height);
	}
	public static Body buildBox(BodyDef.BodyType type, float positionX, float positionY, float width, float height) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(positionX, positionY);
		bodyDef.angle = 0;
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}
	public static Body buildCircle(BodyDef.BodyType type, Vector2 position, float radius) {
		resetBodyDef();

		bodyDef.type = type;
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		circleShape.setRadius(radius);
		fixtureDef.shape = circleShape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		body.createFixture(fixtureDef);

		return body;
	}
	public static Body buildSensorCircle(BodyDef.BodyType type, Vector2 position, float radius) {
		resetBodyDef();

		bodyDef.type = type;
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.isSensor = true;
		circleShape.setRadius(radius);
		fixtureDef.shape = circleShape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		body.createFixture(fixtureDef);

		return body;
	}
	public static Body buildDynamicCircle(float x, float y, float radius) {
		resetBodyDef();

		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);

		circleShape.setRadius(radius);

		fixtureDef.shape = circleShape;

		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);

		return body;
	}
	public static Body buildBulletCircle(float x, float y, float velocityX, float velocityY, float radius) {
		resetBodyDef();

		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.linearVelocity.set(velocityX, velocityY);
		bodyDef.bullet = true;
		bodyDef.fixedRotation = true;
		bodyDef.active = true;

		circleShape.setRadius(radius);

		fixtureDef.shape = circleShape;

		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);

		return body;
	}
	public static Body buildBoxSensor(BodyDef.BodyType type, Vector2 position, float width, float height, float deg) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		bodyDef.angle = deg;
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);
		shape.dispose();

		return body;
	}
	public static Body buildCircleSensor(BodyDef.BodyType type, Vector2 position, float radius) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);
		shape.dispose();
		body.setTransform(position, 0);

		return body;
	}
	public static Fixture buildSensor(Body body, float width, float height) {

		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		Fixture fixtureSensor = body.createFixture(fixtureDef);
		shape.dispose();

		return fixtureSensor;
	}
	private static void resetBodyDef(){
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.gravityScale = 1;
		bodyDef.bullet = false;

		bodyDef.active = true;
		bodyDef.allowSleep = true;

		bodyDef.angle = 0;
		bodyDef.angularDamping = 0;
		bodyDef.angularVelocity = 0;
		bodyDef.fixedRotation = false;

		bodyDef.position.set(0, 0);
		bodyDef.linearDamping = 0;
		bodyDef.linearVelocity.set(0, 0);
	}
}
