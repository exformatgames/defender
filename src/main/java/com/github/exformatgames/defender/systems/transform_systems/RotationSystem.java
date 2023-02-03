package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.light_components.LightComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.RotationComponent;


public class RotationSystem extends IteratingSystem {

    public RotationSystem() {
        super(Family.all(RotationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        RotationComponent rotation = RotationComponent.getComponent(entity);

        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        LightComponent lightComponent = LightComponent.getComponent(entity);

        if (lightComponent != null) {
            lightComponent.light.setDirection(rotation.degres);
        }

        if (spriteComponent != null) {
            for (int i = 0; i < spriteComponent.spriteComponentArray.size; i++) {
                SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);
                sprite.rotation = rotation.degres;
                sprite.dirty = true;
            }
        }

        entity.remove(RotationComponent.class);
    }
}
