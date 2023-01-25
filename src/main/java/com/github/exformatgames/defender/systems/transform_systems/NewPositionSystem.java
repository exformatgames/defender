package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.audio_components.PointSoundComponent;
import com.github.exformatgames.defender.components.box2d.B2DParticleEmitterComponent;
import com.github.exformatgames.defender.components.light_components.LightComponent;
import com.github.exformatgames.defender.components.rendering_components.CameraComponent;
import com.github.exformatgames.defender.components.rendering_components.ParticleEffectComponent;
import com.github.exformatgames.defender.components.rendering_components.ParticleEmitterComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.NewPositionComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.*;

public class NewPositionSystem extends IteratingSystem {

    public NewPositionSystem() {
        super(Family.all(NewPositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {

        NewPositionComponent position = NewPositionComponent.getComponent(entity);

        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        ParticleEmitterComponent particleComponent = ParticleEmitterComponent.getComponent(entity);
        ParticleEffectComponent particleEffectComponent = ParticleEffectComponent.getComponent(entity);
        B2DParticleEmitterComponent b2DParticleComponent = B2DParticleEmitterComponent.getComponent(entity);
        PointSoundComponent pointSoundComponent = PointSoundComponent.getComponent(entity);
        LightComponent lightComponent = LightComponent.getComponent(entity);
        CameraComponent cameraComponent = CameraComponent.getComponent(entity);
        PositionComponent positionComponent = PositionComponent.getComponent(entity);
        SizeComponent sizeComponent = SizeComponent.getComponent(entity);

        if (positionComponent != null) {
            positionComponent.init(position.x, position.y);
        }

        if (particleComponent != null) {
            particleComponent.emitter.setPosition(position.x + sizeComponent.halfWidth, position.y + sizeComponent.halfHeight);
        }

        if (particleEffectComponent != null) {
            particleEffectComponent.pooledEffect.setPosition(position.x + sizeComponent.halfWidth, position.y + sizeComponent.halfHeight);
        }

        if (b2DParticleComponent != null) {
            b2DParticleComponent.emitter.setPosition(position.x, position.y);
        }

        if (pointSoundComponent != null) {
            pointSoundComponent.position.set(position.x, position.y);
        }

        if (lightComponent != null) {
            lightComponent.light.setPosition(position.x, position.y);
        }

        if (cameraComponent != null) {
            float z = cameraComponent.camera.position.z;
            cameraComponent.camera.position.set(position.x, position.y, z);
            cameraComponent.camera.update();
        }

        if (spriteComponent != null) {
            for (int i = 0; i < spriteComponent.spriteComponentArray.size; i++) {
                SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);

                sprite.x = position.x;
                sprite.y = position.y;

                if (sprite.dirty) {
                    continue;
                }
                if (sprite.rotation != 0 || sprite.scaleX != 1 || sprite.scaleY != 1) {
                    sprite.dirty = true;
                    continue;
                }

                float x2 = position.x + sprite.width;
                float y2 = position.y + sprite.height;

                float[] vertices = sprite.getVertices();

                vertices[X1] = position.x + sprite.offsetX;
                vertices[Y1] = position.y + sprite.offsetY;
                vertices[X2] = position.x + sprite.offsetX;
                vertices[Y2] = y2 + sprite.offsetY;
                vertices[X3] = x2 + sprite.offsetX;
                vertices[Y3] = y2 + sprite.offsetY;
                vertices[X4] = x2 + sprite.offsetX;
                vertices[Y4] = position.y + sprite.offsetY;
            }
        }

        entity.remove(NewPositionComponent.class);
    }
}
