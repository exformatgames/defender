package com.github.exformatgames.defender.components.rendering_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraComponent implements Component {

    public Viewport viewport;
    public OrthographicCamera camera;

    public void init(Viewport viewport) {
        this.viewport = viewport;
        camera = (OrthographicCamera) viewport.getCamera();
    }

    private final static ComponentMapper<CameraComponent> mapper = ComponentMapper.getFor(CameraComponent.class);

	public static CameraComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}
