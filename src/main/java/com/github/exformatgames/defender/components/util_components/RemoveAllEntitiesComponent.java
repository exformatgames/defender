package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

public class RemoveAllEntitiesComponent implements Component {

    public final Array<Entity> ignoredEntities = new Array<>();

    private static final ComponentMapper<RemoveAllEntitiesComponent> mapper = ComponentMapper.getFor(RemoveAllEntitiesComponent.class);

    public static RemoveAllEntitiesComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }

}
