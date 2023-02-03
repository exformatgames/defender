package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.light_components.LightComponent;
import com.github.exformatgames.defender.components.rendering_components.CameraComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.RotateComponent;

public class RotateSystem extends IteratingSystem {

    public RotateSystem() {
        super(Family.all(RotateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        RotateComponent rotate = RotateComponent.getComponent(entity);

        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        LightComponent lightComponent = LightComponent.getComponent(entity);
        CameraComponent cameraComponent = CameraComponent.getComponent(entity);

        if (lightComponent != null) {
            float angle = lightComponent.light.getDirection() + rotate.degres;
            lightComponent.light.setDirection(angle);
        }

        if (cameraComponent != null) {
            cameraComponent.camera.rotate(rotate.degres);
            cameraComponent.camera.update();
        }

        if (spriteComponent != null) {
            for (int i = 0; i < spriteComponent.spriteComponentArray.size; i++) {
                SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);
                sprite.rotation += rotate.degres;
                sprite.dirty = true;
            }

        }

        entity.remove(RotateComponent.class);
    }
}
