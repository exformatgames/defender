package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.github.exformatgames.defender.utils.Particles;

public class ParticleEffectComponent implements Component {

    public ParticleEffectPool particleEffectPool;
    public ParticleEffectPool.PooledEffect pooledEffect;
    public boolean isDraw = false;
    public String name = "";


    public ParticleEffectComponent init(String name, float x, float y, boolean start) {
        this.name = name;
        particleEffectPool = Particles.GET(name);
        pooledEffect = particleEffectPool.obtain();
        pooledEffect.setPosition(x, y);

        if (start) {
            pooledEffect.start();
            isDraw = true;
        }

        return this;
    }

    private final static ComponentMapper<ParticleEffectComponent> mapper = ComponentMapper.getFor(ParticleEffectComponent.class);

    public static ParticleEffectComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
