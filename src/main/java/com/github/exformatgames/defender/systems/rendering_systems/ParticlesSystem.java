package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.rendering_components.ParticleEffectComponent;

public class ParticlesSystem extends IteratingSystem {

    public ParticlesSystem() {
        super(Family.all(ParticleEffectComponent.class).get());
    }


    @Override
    protected void processEntity(Entity entity, float dt) {
        ParticleEffectComponent particleComponent = ParticleEffectComponent.getComponent(entity);
        particleComponent.pooledEffect.update(dt);
    }
}
