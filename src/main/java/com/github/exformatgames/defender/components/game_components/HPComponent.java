package com.github.exformatgames.defender.components.game_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class HPComponent implements Component {

    public float HP = 0;

    private final static ComponentMapper<HPComponent> mapper = ComponentMapper.getFor(HPComponent.class);

    public static HPComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
