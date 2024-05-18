package com.github.exformatgames.defender.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pools;

public class BodyBuilder {

	private static World world;

	private static final BodyDef BODY_DEF = new BodyDef();
	private static final FixtureDef FIXTURE_DEF = new FixtureDef();

	public static void init(World world) {
		BodyBuilder.world = world;
	}

	/**
	 * Creating a new body using a model from the Physics Body Editor program with default fixture parameters.
	 * @param loader
	 * @param name
	 * @param type
	 * @param position
	 * @param scl body size multiplier
	 * @return final body for editing parameters
	 */
	public static Body buildModel(BodyEditorLoader loader, String name, BodyDef.BodyType type, Vector2 position, float scl){
		BODY_DEF.type = type;
		BODY_DEF.position.set(position);
		BODY_DEF.angle = 0;
		Body body = world.createBody(BODY_DEF);

		loader.attachFixture(body, name, FIXTURE_DEF, scl);
		return body;
	}

	/**
	 * Creating a new body using a model from the Physics Body Editor program.
	 * @param loader
	 * @param name
	 * @param type
	 * @param position
	 * @param scl body size multiplier
	 * @return final body for editing parameters
	 */
	public static Body buildModel(BodyEditorLoader loader, String name, BodyDef.BodyType type, FixtureDef fixtureDef, Vector2 position, float scl){
		BODY_DEF.type = type;
		BODY_DEF.position.set(position);
		BODY_DEF.angle = 0;
		Body body = world.createBody(BODY_DEF);

		loader.attachFixture(body, name, fixtureDef, scl);
		return body;
	}

	/**
	 * Builds a new bullet, parameters and filter, by default.
	 * @param positionX start position
	 * @param positionY start position
	 * @param velocityX start velocity
	 * @param velocityY start velocity
	 * @param radius
	 * @return final body for editing parameters
	 */
	public static Body buildBullet(float positionX, float positionY, float velocityX, float velocityY, float radius) {
		resetAll();

		BODY_DEF.type = BodyDef.BodyType.DynamicBody;
		BODY_DEF.position.set(positionX, positionY);
		BODY_DEF.linearVelocity.set(velocityX, velocityY);
		BODY_DEF.bullet = true;
		BODY_DEF.fixedRotation = true;
		BODY_DEF.active = true;
        BODY_DEF.gravityScale = 0;

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);

		FIXTURE_DEF.shape = circleShape;
        FIXTURE_DEF.density = 0.1f;
        FIXTURE_DEF.isSensor = true;

		Body body = world.createBody(BODY_DEF);
		body.createFixture(FIXTURE_DEF);

        circleShape.dispose();

		return body;
	}

    public static Body buildKinematicBullet(float positionX, float positionY, float velocityX, float velocityY, float radius) {
		resetAll();

		BODY_DEF.type = BodyDef.BodyType.KinematicBody;
		BODY_DEF.position.set(positionX, positionY);
		BODY_DEF.linearVelocity.set(velocityX, velocityY);
		BODY_DEF.bullet = true;
		BODY_DEF.fixedRotation = true;
		BODY_DEF.active = true;

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);

		FIXTURE_DEF.shape = circleShape;
        FIXTURE_DEF.density = 0.1f;
        FIXTURE_DEF.isSensor = true;

		Body body = world.createBody(BODY_DEF);
		body.createFixture(FIXTURE_DEF);

        circleShape.dispose();

		return body;
	}
    public static Body buildBullet(float positionX, float positionY, float velocityX, float velocityY, float radius, short mask, short cat, short group) {
        resetAll();

        BODY_DEF.type = BodyDef.BodyType.DynamicBody;
        BODY_DEF.position.set(positionX, positionY);
        BODY_DEF.angle = (float)Math.atan2(velocityY, velocityX);
        BODY_DEF.linearVelocity.set(velocityX, velocityY);
        BODY_DEF.bullet = true;
        BODY_DEF.fixedRotation = true;
        BODY_DEF.active = true;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);

        FIXTURE_DEF.shape = circleShape;
        FIXTURE_DEF.density = 0.1f;
        FIXTURE_DEF.isSensor = true;
        FIXTURE_DEF.filter.maskBits = mask;
        FIXTURE_DEF.filter.categoryBits = cat;
        FIXTURE_DEF.filter.groupIndex = group;

        Body body = world.createBody(BODY_DEF);
        body.createFixture(FIXTURE_DEF);

        circleShape.dispose();

        return body;
    }


    /**
	 * Builds a new body, parameters and filter, by default.
	 * @param type
	 * @param positionX
	 * @param positionY
	 * @param width
	 * @param height
	 * @return final body for editing parameters
	 */
	public static Body buildBox(BodyDef.BodyType type, float positionX, float positionY, float width, float height) {
		resetAll();

		BODY_DEF.type = type;
		BODY_DEF.position.set(positionX, positionY);
		BODY_DEF.angle = 0;
		Body body = world.createBody(BODY_DEF);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		FIXTURE_DEF.shape = shape;
		body.createFixture(FIXTURE_DEF);

		shape.dispose();
		return body;
	}

    public static Body buildOval(BodyDef.BodyType type, float positionX, float positionY, float width, float height) {
        resetAll();

        BODY_DEF.type = type;
        BODY_DEF.position.set(positionX, positionY);
        BODY_DEF.angle = 0;

        Body body = world.createBody(BODY_DEF);

        PolygonShape shape = new PolygonShape();
        float h = (height / 2) - width / 2;
        if (h < 0) {
            h = 0.01f;
        }

        shape.setAsBox(width / 2, h);
        FIXTURE_DEF.shape = shape;
        body.createFixture(FIXTURE_DEF);

        shape.dispose();

        attachCircleFixture(0, h, width / 2, body);
        attachCircleFixture(0, -h, width / 2, body);
        return body;
    }

	/**
	 * Builds a new body, parameters and filter, by default.
	 * @param type
	 * @param positionX
	 * @param positionY
	 * @param radius
	 * @return final body for editing parameters
	 */
	public static Body buildCircle(BodyDef.BodyType type, float positionX,float positionY, float radius) {
		return buildCircle(type, positionX, positionY, 0, radius);
	}
    public static Body buildCircle(BodyDef.BodyType type, float positionX,float positionY, float rotation, float radius) {
        resetAll();

        BODY_DEF.type = type;
        BODY_DEF.position.set(positionX, positionY);
        BODY_DEF.angle = rotation;
        Body body = world.createBody(BODY_DEF);

        CircleShape circleShape = new CircleShape();

        circleShape.setRadius(radius);
        FIXTURE_DEF.shape = circleShape;
        body.createFixture(FIXTURE_DEF);

        circleShape.dispose();

        return body;
    }
	/**
	 * Builds a new body based on the sensor, parameters and filter, by default.
	 * @param type
	 * @param positionX
	 * @param positionY
	 * @param width
	 * @param height
	 * @return final body for editing parameters
	 */
	public static Body buildBoxSensor(BodyDef.BodyType type, float positionX, float positionY, float width, float height) {
		resetAll();

		BODY_DEF.type = type;
		BODY_DEF.position.set(positionX, positionY);

		Body body = world.createBody(BODY_DEF);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		FIXTURE_DEF.shape = shape;
		FIXTURE_DEF.isSensor = true;
		body.createFixture(FIXTURE_DEF);

		shape.dispose();

		return body;
	}

	/**
	 * Builds a new body based on the sensor, parameters and filter, by default.
	 * @param type
	 * @param positionX
	 * @param positionY
	 * @param radius
	 * @return final body for editing parameters
	 */
	public static Body buildCircleSensor(BodyDef.BodyType type, float positionX, float positionY, float radius) {
		resetAll();

		BODY_DEF.type = type;
		BODY_DEF.position.set(positionX, positionY);
		Body body = world.createBody(BODY_DEF);

		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		FIXTURE_DEF.shape = shape;
		FIXTURE_DEF.isSensor = true;

		body.createFixture(FIXTURE_DEF);
		shape.dispose();

		return body;
	}


	/**
	 * method adds a new sensor to an existing body with default parameters and a new contact filter
	 * @param body
	 * @param width
	 * @param height
	 * @param filter
	 * @return final fixture for editing parameters
	 */
	public static Fixture addBoxSensor(Body body, float width, float height, Filter filter) {
		resetFixtureDef();

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width / 2, height / 2);
		FIXTURE_DEF.shape = polygonShape;
		FIXTURE_DEF.isSensor = true;
		Fixture fixtureSensor = body.createFixture(FIXTURE_DEF);
		fixtureSensor.setFilterData(filter);

		polygonShape.dispose();

		return fixtureSensor;
	}

	/**
	 * method adds a new sensor to an existing body with default parameters and a filter from the first fixture of the body
	 * @param body
	 * @param width
	 * @param height
	 * @return final fixture for editing parameters
	 */
	public static Fixture addBoxSensor(Body body, float width, float height) {
		return addBoxSensor(body, width, height, (Filter) body.getFixtureList().first().getFilterData());
	}

	/**
	 * method adds a new sensor to an existing body with default parameters and a new contact filter
	 * @param body
	 * @param radius
	 * @param filter
	 * @return final fixture for editing parameters
	 */
	public static Fixture addCircleSensor(Body body, float radius, Filter filter) {
		resetFixtureDef();

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);
		FIXTURE_DEF.shape = circleShape;
        FIXTURE_DEF.density = 0;
		FIXTURE_DEF.isSensor = true;
		Fixture fixtureSensor = body.createFixture(FIXTURE_DEF);
		fixtureSensor.setFilterData(filter);

		circleShape.dispose();

		return fixtureSensor;
	}

	/**
	 * method adds a new sensor to an existing body with default parameters and a filter from the first fixture of the body
	 * @param body
	 * @param radius
	 * @return final fixture for editing parameters
	 */
	public static Fixture addCircleSensor(Body body, float radius) {
		return addCircleSensor(body, radius, (Filter) body.getFixtureList().first().getFilterData());
	}


	/**
	 * method adds a new fixture to an existing body with default parameters and a new contact filter	 * @param body
	 * @param width
	 * @param height
	 * @param filter
	 * @return final fixture for editing parameters
	 */
	public static Fixture addBoxFixture(Body body, float width, float height, Filter filter) {
		resetFixtureDef();

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(width / 2, height / 2);
		FIXTURE_DEF.shape = polygonShape;
		FIXTURE_DEF.isSensor = false;
		Fixture fixtureSensor = body.createFixture(FIXTURE_DEF);
		fixtureSensor.setFilterData(filter);

		polygonShape.dispose();

		return fixtureSensor;
	}

	/**
     * method adds a new fixture to an existing body with default parameters and a filter from the first body fixture	 * @param body
     *
     * @param width
     * @param height
     * @return final fixture for editing parameters
     */
	public static Fixture addBoxFixture(Body body, float width, float height) {
		return addBoxFixture(body, width, height, (Filter) body.getFixtureList().first().getFilterData());
	}

	/**
	 * method adds a new fixture to an existing body with default parameters and a new contact filter	 * @param body
	 * @param radius
	 * @param filter
	 * @return final fixture for editing parameters
	 */
	public static Fixture addCircleFixture(Body body, float radius, Filter filter) {
		resetFixtureDef();

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(radius);
		FIXTURE_DEF.shape = circleShape;
		FIXTURE_DEF.isSensor = false;
		Fixture fixtureSensor = body.createFixture(FIXTURE_DEF);
		fixtureSensor.setFilterData(filter);

		circleShape.dispose();

		return fixtureSensor;
	}

	/**
	 * method adds a new fixture to an existing body with default parameters and a filter from the first body fixture	 * @param body
	 * @param radius
	 * @return final fixture for editing parameters
	 */
	public static Fixture addCircleFixture(Body body, float radius) {
		return addCircleFixture(body, radius, (Filter) body.getFixtureList().first().getFilterData());
	}

    public static Body createEmptyBody(BodyDef.BodyType type) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;

        return world.createBody(bodyDef);
    }

    public static void attachBoxFixture(float x, float y, float size, Body body) {
        attachBoxFixture(x, y, size, size, body);
    }

    public static void attachBoxFixture(float x, float y, float width, float height, Body body) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = false;
        PolygonShape polygonShape = new PolygonShape();

        Vector2 lb = Pools.obtain(Vector2.class);
        Vector2 lt = Pools.obtain(Vector2.class);
        Vector2 rb = Pools.obtain(Vector2.class);
        Vector2 rt = Pools.obtain(Vector2.class);

        lb.set(x, y);
        lt.set(x, y + height);
        rb.set(x + width, y);
        rt.set(x + width, y + height);

        Vector2[] box = {lb, lt, rb, rt};
        polygonShape.set(box);
        fixtureDef.shape = polygonShape;

        body.createFixture(fixtureDef);

        Pools.free(lb);
        Pools.free(lt);
        Pools.free(rb);
        Pools.free(rt);

        polygonShape.dispose();
    }

    public static void attachCircleFixture(float x, float y, float radius, Body body) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = false;

        Vector2 position = Pools.obtain(Vector2.class);
        position.set(x, y);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        circleShape.setPosition(position);

        fixtureDef.shape = circleShape;

        body.createFixture(fixtureDef);

        Pools.free(position);
        circleShape.dispose();
    }

	protected static void resetBodyDef(){
		BODY_DEF.type = BodyDef.BodyType.DynamicBody;
		BODY_DEF.gravityScale = 1;
		BODY_DEF.bullet = false;

		BODY_DEF.active = true;
		BODY_DEF.allowSleep = true;

		BODY_DEF.angle = 0;
		BODY_DEF.angularDamping = 0;
		BODY_DEF.angularVelocity = 0;
		BODY_DEF.fixedRotation = false;

		BODY_DEF.position.set(0, 0);
		BODY_DEF.linearDamping = 0;
		BODY_DEF.linearVelocity.set(0, 0);
	}
	protected static void resetFixtureDef(){
		FIXTURE_DEF.shape = null;
		FIXTURE_DEF.friction = 0.2f;

		FIXTURE_DEF.restitution = 0;
		FIXTURE_DEF.density = 1;
		FIXTURE_DEF.isSensor = false;
		FIXTURE_DEF.filter.categoryBits = 0x0001;
		FIXTURE_DEF.filter.groupIndex = 0;
		FIXTURE_DEF.filter.maskBits = -1;

	}
	protected static void resetAll(){
		resetBodyDef();
		resetFixtureDef();
	}
}
