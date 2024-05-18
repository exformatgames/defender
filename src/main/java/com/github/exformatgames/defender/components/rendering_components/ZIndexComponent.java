package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class ZIndexComponent implements Component, Pool.Poolable {

	public int zIndex = 0;


    @Override
    public void reset() {
        zIndex = 0;
    }

    private static final ComponentMapper<ZIndexComponent> mapper = ComponentMapper.getFor(ZIndexComponent.class);

	public static ZIndexComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
