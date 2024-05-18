package com.github.exformatgames.defender.components.game_components.state;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class StateComponent implements Component, Pool.Poolable {

    public State state = State.IDLE;
    public State oldState = State.IDLE;


    @Override
    public void reset() {
        state = State.IDLE;
    }

    private final static ComponentMapper<StateComponent> mapper = ComponentMapper.getFor(StateComponent.class);
    public static StateComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }


    public enum State {
        RUN,
        CROUCH,
        JUMP,
        DOUBLE_JUMP,
        FIRE,
        HIT,
        DEATH,
        FLY,
        IDLE;
    }
}
