package com.github.exformatgames.defender.components.tiled_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class TiledMapComponent implements Component {

    public TiledMap tiledMap;

    private final static ComponentMapper<TiledMapComponent> mapper = ComponentMapper.getFor(TiledMapComponent.class);

    public static TiledMapComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
