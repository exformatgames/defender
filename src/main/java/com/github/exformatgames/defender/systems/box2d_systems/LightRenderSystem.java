package com.github.exformatgames.defender.systems.box2d_systems;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

public class LightRenderSystem extends IteratingSystem {

	private final OrthographicCamera camera;
	public static RayHandler rayHandler;

	public LightRenderSystem(OrthographicCamera camera, World world) {
		super(Family.all().get());
		this.camera = camera;

		rayHandler = new RayHandler(world);
		rayHandler.setAmbientLight(0.7f, 0.7f, 0.7f, 0.9f);

		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		rayHandler.setBlurNum(3);
		rayHandler.setCulling(true);
	}

	public LightRenderSystem(OrthographicCamera camera, RayHandler rayHandler) {
		super(Family.all().get());

		this.camera = camera;
		LightRenderSystem.rayHandler = rayHandler;
	}

	@Override
	public void endProcessing() {
		rayHandler.setCombinedMatrix(camera);
		rayHandler.updateAndRender();
	}



	@Override
	protected void processEntity(Entity entity, float dt) {

	}
}
