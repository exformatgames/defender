package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class AsyncSortRenderSystem extends SortedRenderSystem {

    public AsyncSortRenderSystem(OrthographicCamera camera, SpriteBatch batch) {
        super(camera, batch);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.app.postRunnable(() -> {
                    ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
                    AsyncSortRenderSystem.super.update(deltaTime);
                }
        );
    }
}
