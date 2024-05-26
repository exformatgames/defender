package com.github.exformatgames.defender.components.input_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class DragAndDropComponent implements Component {

    public Vector2 dragStart = new Vector2();
    public Vector2 dragPosition = new Vector2();
    public Vector2 dragDelta = new Vector2();
    public Vector2 dragStop = new Vector2();

    public boolean isDragging = false;

    private final static ComponentMapper<DragAndDropComponent> mapper = ComponentMapper.getFor(DragAndDropComponent.class);

    public static DragAndDropComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
