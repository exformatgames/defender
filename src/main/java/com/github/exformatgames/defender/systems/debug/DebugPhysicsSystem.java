package com.github.exformatgames.defender.systems.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.components.box2d.WorldComponent;

public class DebugPhysicsSystem extends IteratingSystem {

	private final Box2DDebugRenderer debugRenderer;
	private final OrthographicCamera camera;
    private final Viewport viewport;

	public DebugPhysicsSystem(Viewport viewport, OrthographicCamera camera) {
		super(Family.all(WorldComponent.class).get());
		debugRenderer = new Box2DDebugRenderer();
		this.camera = camera;
        this.viewport = viewport;
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		WorldComponent worldComponent = WorldComponent.getComponent(entity);
        viewport.apply();
		debugRenderer.render(worldComponent.world, viewport.getCamera().combined);
	}
}
