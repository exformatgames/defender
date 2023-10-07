package com.github.exformatgames.defender.utils;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.AnimationComponent;

public abstract class EntityBuilder {
	protected static World world;
	protected static PooledEngine engine;
	protected static TextureAtlas textureAtlas;
	protected static OrthographicCamera camera;
	protected static AssetManager assetManager;

	protected static BodyDef BODY_DEF;
	protected static FixtureDef FIXTURE_DEF;

	protected static CircleShape CIRCLE_SHAPE;
	protected static PolygonShape POLYGON_SHAPE;
	protected static ChainShape CHAIN_SHAPE;
	protected static EdgeShape EDGE_SHAPE;

	private final static Vector2 zeroVector = new Vector2();
	
	public static void init(World world, PooledEngine engine, TextureAtlas textureAtlas, OrthographicCamera camera, AssetManager assetManager){

		if (world != null) {
			BODY_DEF = new BodyDef();
			FIXTURE_DEF = new FixtureDef();
			CIRCLE_SHAPE = new CircleShape();
			POLYGON_SHAPE = new PolygonShape();
			CHAIN_SHAPE = new ChainShape();
			EDGE_SHAPE = new EdgeShape();
		}

		EntityBuilder.world = world;
		EntityBuilder.engine = engine;
		EntityBuilder.textureAtlas = textureAtlas;
		EntityBuilder.camera = camera;
		EntityBuilder.assetManager = assetManager;
	}

	protected Entity entity;

	public EntityBuilder(){
		if (engine != null) {
			entity = engine.createEntity();
			engine.addEntity(entity);
		}
	}

	public abstract void create();
	
	public void create(Vector2 position){}
	
	public void create(String JSON){}

	public static  <T extends Component> T createComponent (Entity entity, Class<T> componentType) {
		T type = engine.createComponent(componentType);

		if (type instanceof SpriteComponent){
			SpriteComponent owner = entity.getComponent(SpriteComponent.class);
			if (owner != null) {
				owner.spriteComponentArray.add((SpriteComponent) type);
			}
			else {
				((SpriteComponent) type).spriteComponentArray.clear();
				((SpriteComponent) type).spriteComponentArray.add((SpriteComponent) type);
				entity.add(engine.createComponent(ZIndexComponent.class));
				entity.add(type);
			}

			return type;
		}

		if (type instanceof AnimationComponent){
			AnimationComponent owner = entity.getComponent(AnimationComponent.class);
			if (owner != null) {
				owner.animationComponentArray.add((AnimationComponent) type);
			}
			else {
				((AnimationComponent) type).animationComponentArray.clear();
				((AnimationComponent) type).animationComponentArray.add((AnimationComponent) type);
				entity.add(type);
			}
			return type;
		}

		if (type instanceof BodyComponent) {
			((BodyComponent) type).setEntityInUserData(entity);
		}

		entity.add(type);
		return type;
	}

	public <T extends Component> T createComponent (Class<T> componentType) {
		T type = engine.createComponent(componentType);

		if (type instanceof SpriteComponent){
			SpriteComponent owner = entity.getComponent(SpriteComponent.class);
			if (owner != null) {
				owner.spriteComponentArray.add((SpriteComponent) type);
			}
			else {
				((SpriteComponent) type).spriteComponentArray.clear();
				((SpriteComponent) type).spriteComponentArray.add((SpriteComponent) type);
				entity.add(engine.createComponent(ZIndexComponent.class));
				entity.add(type);
			}
			return type;
		}

		if (type instanceof AnimationComponent){
			AnimationComponent owner = entity.getComponent(AnimationComponent.class);
			if (owner != null) {
				owner.animationComponentArray.add((AnimationComponent) type);
			}
			else {
				((AnimationComponent) type).animationComponentArray.clear();
				((AnimationComponent) type).animationComponentArray.add((AnimationComponent) type);
				entity.add(type);
			}
			return type;
		}

		if (type instanceof BodyComponent) {
			((BodyComponent) type).setEntityInUserData(entity);
		}

		entity.add(type);
		return type;
	}

	public Animation<TextureAtlas.AtlasRegion> createAnimation(String name, float frameDuration){
		return new Animation<>(frameDuration, textureAtlas.findRegions(name).toArray());
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
		FIXTURE_DEF.density = 0;
		FIXTURE_DEF.isSensor = false;
		FIXTURE_DEF.filter.categoryBits = 0x0001;
		FIXTURE_DEF.filter.groupIndex = 0;
		FIXTURE_DEF.filter.maskBits = -1;

	}

	protected static void resetShapes(){
		CIRCLE_SHAPE.setPosition(zeroVector);
		CIRCLE_SHAPE.setRadius(0);

		POLYGON_SHAPE.setAsBox(0, 0);

		CHAIN_SHAPE.clear();

		EDGE_SHAPE.set(zeroVector, zeroVector);
	}

	protected static void resetAll(){
		resetBodyDef();
		resetFixtureDef();
		resetShapes();
	}
}
