package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.github.exformatgames.defender.components.audio_components.PointSoundComponent;
import com.github.exformatgames.defender.components.box2d.B2DParticleEmitterComponent;
import com.github.exformatgames.defender.components.light_components.LightComponent;
import com.github.exformatgames.defender.components.rendering_components.CameraComponent;
import com.github.exformatgames.defender.components.rendering_components.ParticleEmitterComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.TranslateComponent;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.*;

public class TranslateSystem extends IteratingSystem {

    public TranslateSystem() {
        super(Family.all(TranslateComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        TranslateComponent translate = TranslateComponent.getComponent(entity);

        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        ParticleEmitterComponent particleComponent = ParticleEmitterComponent.getComponent(entity);
        B2DParticleEmitterComponent b2DParticleComponent = B2DParticleEmitterComponent.getComponent(entity);
        PointSoundComponent pointSoundComponent = PointSoundComponent.getComponent(entity);
        LightComponent lightComponent = LightComponent.getComponent(entity);
        CameraComponent cameraComponent = CameraComponent.getComponent(entity);
        PositionComponent positionComponent = PositionComponent.getComponent(entity);

        if (positionComponent != null) {
            positionComponent.x += translate.x;
            positionComponent.y += translate.y;
        }

        if (particleComponent != null) {
            float x = particleComponent.emitter.getX() + translate.x;
            float y = particleComponent.emitter.getY() + translate.y;

            particleComponent.emitter.setPosition(x, y);
        }

        if (b2DParticleComponent != null) {
            float x = b2DParticleComponent.emitter.getX() + translate.x;
            float y = b2DParticleComponent.emitter.getY() + translate.y;

            b2DParticleComponent.emitter.setPosition(x, y);
        }

        if (pointSoundComponent != null) {
            pointSoundComponent.position.add(translate.x, translate.y);
        }

        if (lightComponent != null && lightComponent.light.getBody() == null) {
            Vector2 position = lightComponent.light.getPosition();
            position.add(translate.x, translate.y);
            lightComponent.light.setPosition(position);
        }

        if (cameraComponent != null) {
            cameraComponent.camera.translate(translate.x, translate.y);
            cameraComponent.camera.update();
        }

        if (spriteComponent != null) {
            for (int i = 0; i < spriteComponent.spriteComponentArray.size; i++) {
                SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);
                sprite.y += translate.y;
                sprite.x += translate.x;

                if (sprite.dirty) {
                    continue;
                }
                if (sprite.rotation != 0 || sprite.scaleX != 1 || sprite.scaleY != 1) {
                    sprite.dirty = true;
                    continue;
                }

                float[] vertices = sprite.getVertices();

                vertices[X1] += translate.x;
                vertices[Y1] += translate.y;
                vertices[X2] += translate.x;
                vertices[Y2] += translate.y;
                vertices[X3] += translate.x;
                vertices[Y3] += translate.y;
                vertices[X4] += translate.x;
                vertices[Y4] += translate.y;
            }
        }

        entity.remove(TranslateComponent.class);
    }
}

