package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.components.box2d.B2DParticleEmitterComponent;
import com.github.exformatgames.defender.components.rendering_components.*;
import com.github.exformatgames.defender.utils.ZComparator;
import com.github.exformatgames.defender.utils.Uniform;

public class SortedRenderSystem extends SortedIteratingSystem {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public static float FRAME_TIME = 0;
    private long startTime = 0;

    public SortedRenderSystem(Viewport viewport, SpriteBatch batch) {
        super(Family.one(SpriteComponent.class, LightRenderComponent.class).get(), new ZComparator());

        this.batch = batch;
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.viewport = viewport;
    }

    @Override
    public void startProcessing() {
        startTime = TimeUtils.nanoTime();

        viewport.apply(true);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    @Override
    public void endProcessing() {
        batch.end();
        FRAME_TIME = (TimeUtils.nanoTime() - startTime) / 1000_000f;
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        ParticleEffectComponent particleComponent = ParticleEffectComponent.getComponent(entity);
        B2DParticleEmitterComponent b2dEmitter = B2DParticleEmitterComponent.getComponent(entity);
        ShaderComponent shaderComponent = ShaderComponent.getComponent(entity);
        LightRenderComponent lightRenderComponent = LightRenderComponent.getComponent(entity);
        CullingComponent cullingComponent = CullingComponent.getComponent(entity);


        if (spriteComponent != null && (cullingComponent == null || cullingComponent.inViewport)) {
            applyShader(shaderComponent);

            for (int i = 0; i < spriteComponent.spriteComponentArray.size; i++) {
                SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);

                batch.draw(sprite.texture, sprite.getVertices(), 0, SpriteComponent.SPRITE_SIZE);
            }
        }

        if (shaderComponent != null)
            batch.setShader(null);

        if (particleComponent != null && particleComponent.isDraw)
            particleComponent.pooledEffect.draw(batch);

        if (b2dEmitter != null)
            b2dEmitter.emitter.draw(batch);

        if (lightRenderComponent != null) {
            batch.end();
            LightRenderComponent.RAY_HANDLER.setCombinedMatrix(camera);
            LightRenderComponent.RAY_HANDLER.updateAndRender();
            batch.begin();
        }
    }

    private void applyShader(ShaderComponent shaderComponent) {
        if (shaderComponent != null) {
            batch.setShader(shaderComponent.shader);
            for (int i = 0; i < shaderComponent.uniforms.size(); i++) {
                Uniform uniform = shaderComponent.uniforms.get(i);
                if (uniform.vec2 != null)
                    shaderComponent.shader.setUniformf(uniform.location, uniform.vec2);
                else if (uniform.vec3 != null)
                    shaderComponent.shader.setUniformf(uniform.location, uniform.vec3);
                else if (uniform.color != null)
                    shaderComponent.shader.setUniformf(uniform.location, uniform.color);
                else
                    shaderComponent.shader.setUniformf(uniform.location, uniform.value);
            }
        }
    }
}
