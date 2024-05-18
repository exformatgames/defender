package com.github.exformatgames.defender.components.game_components.state;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class CrouchStateComponent implements Component {

    public float xDir = 0;
    public float yDir = 0;

    public void init(float xDir, float yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    private final static ComponentMapper<CrouchStateComponent> mapper = ComponentMapper.getFor(CrouchStateComponent.class);

    public static CrouchStateComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
