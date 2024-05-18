package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;

public class ParticleEmitterComponent implements Component {

	public ParticleEmitter emitter = null;
	public String name;


	private final static ComponentMapper<ParticleEmitterComponent> mapper = ComponentMapper.getFor(ParticleEmitterComponent.class);

	public static ParticleEmitterComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
