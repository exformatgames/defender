package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.github.exformatgames.defender.components.rendering_components.CullingComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.ScrollComponent;
import com.github.exformatgames.defender.components.transform_components.TranslateComponent;

public class ScrollSystem extends IteratingSystem {

	private final OrthographicCamera camera;

	public ScrollSystem(OrthographicCamera camera){
		super(Family.all(ScrollComponent.class).get());
		this.camera = camera;
	}


	@Override
	protected void processEntity(Entity entity, float dt) {
		ScrollComponent scrollComponent = ScrollComponent.getComponent(entity);
		SpriteComponent spriteComponent = SpriteComponent.getComponent(entity); //TODO hmmmmmmm not good
		TranslateComponent translateComponent = TranslateComponent.getComponent(entity);
		CullingComponent cullingComponent = CullingComponent.getComponent(entity);

		if(spriteComponent != null && !cullingComponent.inViewport){
			if(spriteComponent.x < camera.position.x - camera.viewportWidth / 2){//TODO || spriteComponent.y < camera.position.y - camera.viewportHeight / 2){
				float x = scrollComponent.horizontal ? scrollComponent.moveByX : 0;
				float y = scrollComponent.vertical ? scrollComponent.moveByY : 0;

				if(translateComponent == null){
					translateComponent = getEngine().createComponent(TranslateComponent.class);
					translateComponent.x = x;
					translateComponent.y = y;

					entity.add(translateComponent);
				}else{
					translateComponent.x += x;
					translateComponent.y += y;
				}
			}
		}
	}
}
