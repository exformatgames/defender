package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class AsyncOrthoMapRenderSystem extends OrthogonalMapRenderSystem {

    public AsyncOrthoMapRenderSystem(OrthographicCamera camera) {
        super(camera);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.app.postRunnable(() -> {
                ScreenUtils.clear(0f, 0f, 0f, 1f);
                AsyncOrthoMapRenderSystem.super.update(deltaTime);
            }
        );
    }
}
