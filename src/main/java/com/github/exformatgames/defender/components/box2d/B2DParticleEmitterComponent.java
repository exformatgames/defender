package com.github.exformatgames.defender.components.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.graphics.ParticleEmitterBox2D;

public class B2DParticleEmitterComponent implements Component {

	public ParticleEmitterBox2D emitter = null;

	private final static ComponentMapper<B2DParticleEmitterComponent> mapper = ComponentMapper.getFor(B2DParticleEmitterComponent.class);

	public static B2DParticleEmitterComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
