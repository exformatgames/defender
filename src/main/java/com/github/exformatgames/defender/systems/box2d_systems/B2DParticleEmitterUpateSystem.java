package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.box2d.B2DParticleEmitterComponent;

public class B2DParticleEmitterUpateSystem extends IteratingSystem {

    public B2DParticleEmitterUpateSystem() {
        super(Family.all(B2DParticleEmitterComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        B2DParticleEmitterComponent part = B2DParticleEmitterComponent.getComponent(entity);
        part.emitter.update(dt);
    }
}
