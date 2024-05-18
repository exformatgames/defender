package com.github.exformatgames.defender.components.game_components.state;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class JumpComponent implements Component {

    public float xImpulse = 0;
    public float yImpulse = 0;

    public void init(float xImpulse, float yImpulse) {
        this.xImpulse = xImpulse;
        this.yImpulse = yImpulse;
    }

    private final static ComponentMapper<JumpComponent> mapper = ComponentMapper.getFor(JumpComponent.class);

    public static JumpComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
