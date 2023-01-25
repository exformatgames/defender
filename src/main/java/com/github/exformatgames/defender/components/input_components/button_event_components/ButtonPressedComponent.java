package com.github.exformatgames.defender.components.input_components.button_event_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class ButtonPressedComponent implements Component {

    public Array<Integer> buttons = new Array<>();
    public Vector2 mousePosition = new Vector2();

    private final static ComponentMapper<ButtonPressedComponent> mapper = ComponentMapper.getFor(ButtonPressedComponent.class);

    public static ButtonPressedComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
