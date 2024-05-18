package com.github.exformatgames.defender.systems.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DebugLightSystem extends IteratingSystem {

	private final OrthographicCamera camera;
	private final ShapeRenderer ren;



	public DebugLightSystem(OrthographicCamera camera, int priority){
		super(Family.all().get(), priority);

		this.camera = camera;
		ren = new ShapeRenderer();
	}

	@Override
	public void update(float deltaTime) {


		ren.setProjectionMatrix(camera.combined);

	}



	@Override
	protected void processEntity(Entity entity, float dt) {

	}
}
