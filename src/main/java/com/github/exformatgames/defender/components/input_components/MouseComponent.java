package com.github.exformatgames.defender.components.input_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class MouseComponent implements Component {

    public final Vector2 position = new Vector2();

    private final static ComponentMapper<MouseComponent> mapper = ComponentMapper.getFor(MouseComponent.class);

    public static MouseComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
