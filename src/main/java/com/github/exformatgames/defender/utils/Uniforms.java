package com.github.exformatgames.defender.utils;

import com.badlogic.gdx.utils.*;

public class Uniforms {
    private final Array<Uniform> uniforms = new Array<>();

    public Uniform get(int id) {
        return uniforms.get(id);
    }

    public void add(Uniform u) {
        u.id = uniforms.size;
        uniforms.add(u);
    }

    public int size() {
        return uniforms.size;
    }

    public void remove(int id) {
        uniforms.removeIndex(id);
    }

    public void removeAll() {
        uniforms.clear();
    }
}
