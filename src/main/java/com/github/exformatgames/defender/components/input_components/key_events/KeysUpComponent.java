package com.github.exformatgames.defender.components.input_components.key_events;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class KeysUpComponent implements Component {
    public Array<Integer> keys = new Array<>();

    private final static ComponentMapper<KeysUpComponent> mapper = ComponentMapper.getFor(KeysUpComponent.class);

    public static KeysUpComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }

}
