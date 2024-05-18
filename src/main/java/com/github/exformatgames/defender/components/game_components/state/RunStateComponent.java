package com.github.exformatgames.defender.components.game_components.state;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class RunStateComponent implements Component {

    public float xDir = 0;
    public float yDir = 0;

    public void init(float xDir, float yDir) {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    private final static ComponentMapper<RunStateComponent> mapper = ComponentMapper.getFor(RunStateComponent.class);

    public static RunStateComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
