package com.github.exformatgames.defender.utils;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.ArrayMap;

public class Particles {

    private static final ArrayMap<String, ParticleEffectPool> particlesArrayMap = new ArrayMap<>();

    public static int PUT(String key, ParticleEffectPool value) {
        return particlesArrayMap.put(key, value);
    }

    public static ParticleEffectPool GET(String key) {
        return particlesArrayMap.get(key);
    }

    public static ArrayMap<String, ParticleEffectPool> getParticlesArrayMap() {
        return particlesArrayMap;
    }
}
