package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class OrthoMapRenderComponent implements Component {

    public OrthogonalTiledMapRenderer mapRenderer;

    private final static ComponentMapper<OrthoMapRenderComponent> mapper = ComponentMapper.getFor(OrthoMapRenderComponent.class);

    public static OrthoMapRenderComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}
