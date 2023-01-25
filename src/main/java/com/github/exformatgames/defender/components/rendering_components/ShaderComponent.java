package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.ashley.core.*;
import com.github.exformatgames.defender.utils.Uniforms;

public class ShaderComponent implements Component {

    public ShaderProgram shader = null;
    public Uniforms uniforms = new Uniforms();

    private final static ComponentMapper<ShaderComponent> mapper = ComponentMapper.getFor(ShaderComponent.class);

    public static ShaderComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
