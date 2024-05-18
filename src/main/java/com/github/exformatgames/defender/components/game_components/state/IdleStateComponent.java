package com.github.exformatgames.defender.components.game_components.state;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class IdleStateComponent implements Component {

    private final static ComponentMapper<IdleStateComponent> mapper = ComponentMapper.getFor(IdleStateComponent.class);

    public static IdleStateComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
