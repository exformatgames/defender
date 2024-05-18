package com.github.exformatgames.defender.utils;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.assets.Assets;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;
import com.github.exformatgames.defender.components.transform_components.AnimationComponent;

public abstract class EntityBuilder {
	public static World world;
	protected static PooledEngine engine;
	protected static OrthographicCamera camera;
    protected static Viewport viewport;
    protected static Assets assets;

	public static void init(World world, PooledEngine engine, Assets assets, Viewport viewport){
		EntityBuilder.world = world;
		EntityBuilder.engine = engine;
        EntityBuilder.viewport = viewport;
        EntityBuilder.camera = (OrthographicCamera) viewport.getCamera();
		EntityBuilder.assets = assets;
	}

	protected Entity entity;

	public EntityBuilder(){
		if (engine != null) {
			entity = engine.createEntity();
			engine.addEntity(entity);
		}
	}

	public abstract void create();
	public void create(Vector2 position){
        if (engine != null) {
            entity = engine.createEntity();
            engine.addEntity(entity);
        }
    }

    public void create(float x, float y){
        if (engine != null) {
            entity = engine.createEntity();
            engine.addEntity(entity);
        }
    }

    public void createEntity(){
        if (engine != null) {
            entity = engine.createEntity();
            engine.addEntity(entity);
        }
    }

    public static Entity getCreatedEntity(){
        if (engine != null) {
            Entity entity = engine.createEntity();
            engine.addEntity(entity);

            return entity;
        }

        return null;
    }


	public static  <T extends Component> T addComponent(Entity entity, Class<T> componentType) {
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

	public <T extends Component> T addComponent(Class<T> componentType) {
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

	public Animation<TextureAtlas.AtlasRegion> addAnimation(String name, float frameDuration){
		return new Animation<>(frameDuration, assets.getTextureAtlas().findRegions(name).toArray());
	}
}
