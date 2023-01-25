package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.light_components.LightComponent;
import com.github.exformatgames.defender.components.rendering_components.ParticleEffectComponent;
import com.github.exformatgames.defender.components.util_components.RemoveEntityComponent;
import com.github.exformatgames.defender.utils.Particles;

public class RemoveEntitySystem extends IteratingSystem {

    private final World box2dWorld;

    public RemoveEntitySystem(World box2dWorld) {
        super(Family.one(RemoveEntityComponent.class).get());
        this.box2dWorld = box2dWorld;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RemoveEntityComponent removeEntityComponent = RemoveEntityComponent.getComponent(entity);
        if ((removeEntityComponent.timer -= deltaTime) < 0){

            BodyComponent bodyComponent = BodyComponent.getComponent(entity);
            if (bodyComponent != null){
                bodyComponent.body.setActive(false);
                box2dWorld.destroyBody(bodyComponent.body);
            }

            LightComponent lightComponent = LightComponent.getComponent(entity);
            if (lightComponent != null) {
                lightComponent.light.remove();
            }

            ParticleEffectComponent particleEmitterComponent = ParticleEffectComponent.getComponent(entity);
            if (particleEmitterComponent != null){
                Particles.GET(particleEmitterComponent.name).free(particleEmitterComponent.pooledEffect);

                particleEmitterComponent.isDraw = false;
            }

            entity.removeAll();

            getEngine().removeEntity(entity);
        }
    }
}
