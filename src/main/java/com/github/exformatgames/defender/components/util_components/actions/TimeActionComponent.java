package com.github.exformatgames.defender.components.util_components.actions;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class TimeActionComponent implements Component {

    public float timer = 0;

    private static final ComponentMapper<TimeActionComponent> mapper = ComponentMapper.getFor(TimeActionComponent.class);

    public static TimeActionComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }

}
