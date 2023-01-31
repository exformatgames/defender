package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.WorldComponent;
import com.github.exformatgames.defender.components.light_components.LightComponent;
import com.github.exformatgames.defender.components.rendering_components.CullingComponent;
import com.github.exformatgames.defender.components.rendering_components.ParticleEffectComponent;
import com.github.exformatgames.defender.components.util_components.RemoveAllEntitiesComponent;
import com.github.exformatgames.defender.utils.Particles;

public class RemoveAllEntitiesSystem extends IteratingSystem {

    private final World box2dWorld;

    public RemoveAllEntitiesSystem(World box2dWorld) {
        super(Family.one(RemoveAllEntitiesComponent.class).get());
        this.box2dWorld = box2dWorld;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        RemoveAllEntitiesComponent component = RemoveAllEntitiesComponent.getComponent(entity);
        for (Entity en: getEngine().getEntities()) {

            if (component.ignoredEntities.contains(en, true) || WorldComponent.getComponent(en) != null) {
                continue;
            }

            BodyComponent bodyComponent = BodyComponent.getComponent(en);
            if (bodyComponent != null){
                bodyComponent.body.setActive(false);
                box2dWorld.destroyBody(bodyComponent.body);
            }

            LightComponent lightComponent = LightComponent.getComponent(en);
            if (lightComponent != null) {
                lightComponent.light.remove();
            }

            ParticleEffectComponent particleEmitterComponent = ParticleEffectComponent.getComponent(en);
            if (particleEmitterComponent != null){
                Particles.GET(particleEmitterComponent.name).free(particleEmitterComponent.pooledEffect);

                particleEmitterComponent.isDraw = false;
            }

            CullingComponent cullingComponent = CullingComponent.getComponent(en);
            if (cullingComponent != null) {
                cullingComponent.inViewport = true;
            }

            en.removeAll();
            getEngine().removeEntity(en);
        }
        entity.removeAll();
    }
}
